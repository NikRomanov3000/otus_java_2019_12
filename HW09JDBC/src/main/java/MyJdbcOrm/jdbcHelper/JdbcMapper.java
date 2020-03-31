package MyJdbcOrm.jdbcHelper;

import MyJdbcOrm.core.annotations.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcMapper {

    public String createSQLSelect(Class<?> someClass) {

        StringBuilder resultQuery = new StringBuilder("select");
        Field[] classFields = someClass.getDeclaredFields();

        if (getFieldWithAnnotation(classFields) == null) {
            throw new RuntimeException("Can not generate SQL query. Annotation @Id not found");
        }

        for (int i = 0; i < classFields.length; i++) {
            if (i == classFields.length - 1) {
                resultQuery.insert(resultQuery.length(), " " + classFields[i].getName());
            } else {
                resultQuery.insert(resultQuery.length(), " " + classFields[i].getName() + ',');
            }
        }
        resultQuery.insert(resultQuery.length(), " from " + someClass.getSimpleName());

        return String.valueOf(resultQuery);
    }

    public String createSQLSelectByParam(Class<?> someClass, String fieldName) {
        Field[] classFields = someClass.getDeclaredFields();
        StringBuilder resultQuery = new StringBuilder(createSQLSelect(someClass));
        boolean checker = checkField(someClass, fieldName);
        if (checker) {
            for (Field field : classFields) {
                if (field.getName().equals(fieldName))
                    resultQuery.insert(resultQuery.length(), " where " + fieldName + " = (?)");
            }
            return String.valueOf(resultQuery);
        } else throw new RuntimeException("Field: " + fieldName + " Not Found");
    }

    public String createSQLInsert(Class<?> someClass) {
        String tableName = someClass.getSimpleName();
        Field[] classFields = someClass.getDeclaredFields();
        StringBuilder resultQuery = new StringBuilder("insert into " + someClass.getSimpleName() + '(');
        Field idField = getFieldWithAnnotation(classFields);

        if (idField == null) {
            throw new RuntimeException("Can not generate SQL query. Annotation @Id not found");
        }

        for (int i = 0; i < classFields.length; i++) {
            if (!classFields[i].equals(idField)) {
                if (i == classFields.length - 1)
                    resultQuery.insert(resultQuery.length(), " " + classFields[i].getName());
                else
                    resultQuery.insert(resultQuery.length(), " " + classFields[i].getName() + ',');
            }
        }
        resultQuery.insert(resultQuery.length(), ')' + " values (?)");

        return String.valueOf(resultQuery);
    }

    public String createSQLUpdate(Class<?> someClass, String fieldName, Object valueForUpdate) {
        StringBuilder resultQuery = new StringBuilder();

        if (checkField(someClass, fieldName)) {
            if (valueForUpdate.getClass().equals(String.class))
                resultQuery.insert(resultQuery.length(), "update " + someClass.getSimpleName() + " set " + fieldName + " = '" + String.valueOf(valueForUpdate) + "'");
            else
                resultQuery.insert(resultQuery.length(), "update " + someClass.getSimpleName() + " set " + fieldName + " = " + String.valueOf(valueForUpdate));
        } else throw new RuntimeException("Can not find field: " + fieldName);

        return String.valueOf(resultQuery.insert(resultQuery.length(), " where id = (?)"));
    }

    public List<String> getListFiledValue(Object obj) {
        List<String> resultList = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(Id.class)) {
                try {
                    resultList.add(String.valueOf(field.get(obj)));
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return resultList;
    }

    private boolean checkField(Class<?> someClass, String myField) {
        Field[] fields = someClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(myField))
                return true;
        }
        return false;
    }

    private Field getFieldWithAnnotation(Field[] fields) {
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Id.class))
                return field;
        }
        return null;
    }
}
