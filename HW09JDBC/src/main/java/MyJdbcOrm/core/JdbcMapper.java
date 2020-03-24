package MyJdbcOrm.core;

import MyJdbcOrm.core.annotations.Id;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class JdbcMapper {

    private Field getFieldWithAnnotation(Field[] fields) {
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Id.class))
                return field;
        }
        return null;
    }

    public String createSQLSelect(Class<?> someClass) {
        StringBuffer resultQuery = new StringBuffer("select");
        Field[] classFields = someClass.getDeclaredFields();

        if (!Objects.nonNull(getFieldWithAnnotation(classFields)))
            throw new RuntimeException("Can not generate SQL query. Annotation @Id not found");

        for (int i = 0; i < classFields.length; i++) {
            if (i == classFields.length - 1)
                resultQuery.insert(resultQuery.length(), " " + classFields[i].getName());
            else
                resultQuery.insert(resultQuery.length(), " " + classFields[i].getName() + ',');
        }
        resultQuery.insert(resultQuery.length(), " from " + someClass.getSimpleName());

        return String.valueOf(resultQuery);
    }

    public String createSQLSelectByParam(Class<?> someClass, String fieldName, Object param) {
        Field[] classFields = someClass.getDeclaredFields();
        StringBuffer resultQuery = new StringBuffer(createSQLSelect(someClass));
        boolean checker = checkField(someClass, fieldName);
        if (checker) {
            for (Field field : classFields) {
                if (field.getName() == fieldName)
                    resultQuery.insert(resultQuery.length(), " where " + fieldName + " = " + String.valueOf(param));
            }
        }
        //return haveField ? String.valueOf(resultQuery) : "Field: " + classFieldName + " Not Found";
        if (checker)
            return String.valueOf(resultQuery);
        else throw new RuntimeException("Field: " + fieldName + " Not Found");
    }

    public String createSQLInsert(Class<?> someClass, List<String> params) {
        String tableName = someClass.getSimpleName();
        Field[] classFields = someClass.getDeclaredFields();
        StringBuffer resultQuery = new StringBuffer("insert into " + someClass.getSimpleName() + '(');
        Field idField = getFieldWithAnnotation(classFields);

        if (!Objects.nonNull(idField))
            throw new RuntimeException("Can not generate SQL query. Annotation @Id not found");

        for (int i = 0; i < classFields.length; i++) {
            if (!classFields[i].equals(idField)) {
                if (i == classFields.length - 1)
                    resultQuery.insert(resultQuery.length(), " " + classFields[i].getName());
                else
                    resultQuery.insert(resultQuery.length(), " " + classFields[i].getName() + ',');
            }
        }
        resultQuery.insert(resultQuery.length(), ')' + " values (");

        for (int i = 0; i < params.size(); i++) {
            if (classFields[i + 1].getType().equals(String.class))
                resultQuery.insert(resultQuery.length(), "'" + String.valueOf(params.get(i)) + "'" + ',');
            else
                resultQuery.insert(resultQuery.length(), String.valueOf(params.get(i)) + ',');
        }

        return String.valueOf(resultQuery.insert(resultQuery.length() - 1, ')').replace(resultQuery.length() - 1, resultQuery.length(), " "));
    }

    public String createSQLUpdate(Class<?> someClass, String fieldName, Object valueForUpdate) {
        StringBuffer resultQuery = new StringBuffer();

        if (checkField(someClass, fieldName)){
            if(valueForUpdate.getClass().equals(String.class))
                resultQuery.insert(resultQuery.length(), "update " + someClass.getSimpleName() + " set " + fieldName + " = '" + String.valueOf(valueForUpdate)+"'");
            else
                resultQuery.insert(resultQuery.length(), "update " + someClass.getSimpleName() + " set " + fieldName + " = " + String.valueOf(valueForUpdate));
        }

        else throw new RuntimeException("Can not find field: " + fieldName);

        return String.valueOf(resultQuery);
    }

    private boolean checkField(Class<?> someClass, String myField) {
        Field[] fields = someClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName() == myField)
                return true;
        }
        return false;
    }

}
