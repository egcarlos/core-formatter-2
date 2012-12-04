/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nutriaderio.coreformatter2;

import com.novatronic.formatter.internal.InternalFormat;

/**
 * Implementación de nuevos métodos para el tipo InternalFormat.
 *
 * @author Carlos Echeverría García
 */
public class InternalFormat2 extends InternalFormat {

    /**
     * Constructor por defecto. Realiza una llamada a super();
     */
    public InternalFormat2() {
        super();
    }

    /**
     * Constructor heredado. Realiza una llamada a super(String id);
     *
     * @param id
     */
    public InternalFormat2(String id) {
        super(id);
    }

    /**
     * Agrega el valor especificado en value navegando por la ruta especificada
     * en path. Si la ruta, o alguno de sus elementos no existe, entonces se
     * crean los elementos.
     *
     * @param path ruta para agregar
     * @param value valor a agregar
     * @return la referencia a si mismo
     */
    public InternalFormat2 add(String path, String value) {
        this.addIntFieldByPath(path, value);
        return this;
    }

    /**
     * Método de sobrecarga que permite agregar un conjunto de valores
     * directamente sobre un path previamente especificado. Por ejemplo una
     * llamada al metodo:<br /><br />
     * <code>
     * internalFormat.add ("path", "campo1", "valor1", "campo2", "valor2");
     * </code><br/><br /> Es equivalente a realizar la siguiente llamada:<br
     * /><br />
     * <code>
     * internalFormat.add("path.campo1", "valor1").add("path.campo2", "valor2");
     * </code>
     *
     * @param pathPrefix ruta sobre la cual agregar los pares de valores
     * @param value datos variables como un flujo de pares de llave valor
     * @return la referencia a sí mismo
     */
    public InternalFormat2 add(String pathPrefix, String... values) {
        return this.add(pathPrefix, AddMode.MAP, values);
    }

    /**
     *
     * @param pathPrefix
     * @param addMode
     * @param values
     * @return
     */
    public InternalFormat2 add(String pathPrefix, AddMode addMode, String... values) {
        switch (addMode) {
            case ARRAY:
                addAsArray(pathPrefix, values);
                break;
            case MAP:
            default:
                addAsMap(pathPrefix, values);

        }
        return this;
    }

    /**
     *
     * @param pathPrefix
     * @param values
     * @return
     */
    public InternalFormat2 addAsMap(String pathPrefix, String... values) {
        if (values.length % 2 != 0) {
            throw new IllegalArgumentException("La cantidad de argumentos variables debe ser múltiplo de 2");
        }
        for (int i = 0; i < values.length; i += 2) {
            String path = pathPrefix + "." + values[i];
            String value = values[i + 1];
            this.add(path, value);
        }
        return this;
    }

    /**
     *
     * @param pathPrefix
     * @param values
     * @return
     */
    public InternalFormat2 addAsArray(String pathPrefix, String... values) {
        for (int i = 0; i < values.length; i++) {
            String path = pathPrefix + "." + i;
            String value = values[i];
            this.add(path, value);
        }
        return this;
    }
}
