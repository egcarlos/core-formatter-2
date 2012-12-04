/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nutriaderio.coreformatter2;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author carloseg
 */
public class InternalFormat2Test {

    InternalFormat2 if2;

    @BeforeMethod
    public void beforeMethod() {
        if2 = new InternalFormat2();
    }

    @Test(dataProvider = "addData")
    public void add(String path, String value) {
        if2.add(path, value);
        String expected = value;
        String actual = if2.getInternalFieldByPath(path).getValue();
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "addAsMapData")
    public void addAsMap(String pathPrefix, String[] values, String[][] assertions) {
        if2.addAsMap(pathPrefix, values);

        for (String[] assertion : assertions) {
            String actual = if2.getInternalFieldByPath(assertion[0]).getValue();
            assertEquals(actual, assertion[1]);
        }
    }

    @Test(dataProvider = "addAsArrayData")
    public void addAsArray(String pathPrefix, String[] values, String[][] assertions) {
        if2.addAsArray(pathPrefix, values);
        for (String[] assertion : assertions) {
            String actual = if2.getInternalFieldByPath(assertion[0]).getValue();
            assertEquals(actual, assertion[1]);
        }
    }

    @DataProvider
    public Object[][] addData() {
        return new Object[][]{
                    new Object[]{"campo", "valor"},
                    new Object[]{"campo.subcampo", "valor"},
                    new Object[]{"campo.subcampo.subcampo", "valor"}
                };
    }

    @DataProvider
    public Object[][] addAsMapData() {
        return new Object[][]{
                    new Object[]{
                        "campo",
                        new String[]{
                            "campo1", "valor1",
                            "campo2", "valor2"
                        },
                        new String[][]{
                            new String[]{"campo.campo1", "valor1"},
                            new String[]{"campo.campo2", "valor2"}
                        }
                    },
                    new Object[]{
                        "campo1.campo2",
                        new String[]{
                            "campo3", "valor3",
                            "campo4", "valor4"
                        },
                        new String[][]{
                            new String[]{"campo1.campo2.campo3", "valor3"},
                            new String[]{"campo1.campo2.campo4", "valor4"}
                        }
                    },
                    new Object[]{
                        "campo1.campo2",
                        new String[]{
                            "campo3.campo4", "valor4",
                            "campo3.campo5", "valor5"
                        },
                        new String[][]{
                            new String[]{"campo1.campo2.campo3.campo4", "valor4"},
                            new String[]{"campo1.campo2.campo3.campo5", "valor5"}
                        }
                    }
                };
    }

    @DataProvider
    public Object[][] addAsArrayData() {
        return new Object[][]{
                    new Object[]{
                        "campo",
                        new String[]{
                            "campo1",
                            "valor1",
                            "campo2",
                            "valor2"
                        },
                        new String[][]{
                            new String[]{"campo.0", "campo1"},
                            new String[]{"campo.1", "valor1"},
                            new String[]{"campo.2", "campo2"},
                            new String[]{"campo.3", "valor2"}
                        }
                    }
                };
    }
}
