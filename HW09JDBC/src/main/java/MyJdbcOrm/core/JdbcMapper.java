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

    public String createSQLSelectByParam(Class<?> someClass, String classFieldName, Object param) {
        Field[] classFields = someClass.getDeclaredFields();
        StringBuffer resultQuery = new StringBuffer(createSQLSelect(someClass));
        boolean haveField = false;
        for (Field field : classFields) {
            field.setAccessible(true);
            if (field.getName().equals(classFieldName)) {
                haveField = true;
                resultQuery.insert(resultQuery.length(), " where " + classFieldName + " = " + String.valueOf(param));
            }
        }
        return haveField ? String.valueOf(resultQuery) : "Field: " + classFieldName + " Not Found";
    }

    public String createSQLInsert(Class<?> someClass, List<Object> params) {
        String tableName = someClass.getSimpleName();
        Field[] classFields = someClass.getDeclaredFields();
        StringBuffer resultQuery = new StringBuffer("insert into " + someClass.getSimpleName()+ '(');
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
        for(Object object : params){
            resultQuery.insert(resultQuery.length(), ')' + " values ("+String.valueOf(object)+',');
        }

        return String.valueOf(resultQuery.insert(resultQuery.length()-1,')').replace(resultQuery.length()-1, resultQuery.length(), " "));
    }
}
