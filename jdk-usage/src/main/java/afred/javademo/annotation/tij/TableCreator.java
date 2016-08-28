package afred.javademo.annotation.tij;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Afred on 15/3/3.
 */
public class TableCreator {

    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("illegal arguments");
            System.exit(0);
        }

        for (String className : args) {
            Class<?> cl = Class.forName(className);

            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (null == dbTable) {
                System.out.println("No DBTable annotations in class " + className);
                continue;
            }

            String tableName = dbTable.name();
            if (tableName.length() < 1) {
                tableName = cl.getName().toLowerCase();
            }

            List<String> columns = new ArrayList<String>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1) {
                    continue;
                }

                if (anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    if (sInt.name().length() < 1) {
                        columnName = field.getName().toLowerCase();
                    } else {
                        columnName = sInt.name();
                    }

                    columns.add(columnName + " int " + getConstraints(sInt.constrains()));
                }

                if (anns[0] instanceof SQLString) {
                    SQLString sStr = (SQLString) anns[0];
                    if (sStr.name().length() < 1) {
                        columnName = field.getName().toLowerCase();
                    } else {
                        columnName = sStr.name();
                    }

                    columns.add(columnName + " varchar(" + sStr.value() + ")" + getConstraints(sStr.constraints()));
                }


            }

            StringBuilder sql = new StringBuilder("create table ");
            sql.append(tableName);
            sql.append("(");
            for (String column : columns) {
                sql.append("\n ");
                sql.append(column);
                sql.append(", ");
            }

            String finalSql = sql.substring(0, sql.length() - 2) + ");";

            System.out.println("final sql : " + finalSql);
        }
    }

    private static String getConstraints(Constraints constraints) {
//        String con = "";

        StringBuffer sb = new StringBuffer();
        if (!constraints.allowNull()) {
            sb.append(" not null");
        }

        if (constraints.primaryKey()) {
            sb.append(" primary key");
        }

        if (constraints.unique()) {
            sb.append(" unique");
        }

        return sb.toString();
    }
}
