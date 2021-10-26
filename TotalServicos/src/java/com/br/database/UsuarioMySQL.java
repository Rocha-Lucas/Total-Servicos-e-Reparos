package com.br.database;

/**
 *
 * @author lucas
 */
public class UsuarioMySQL {
    //Nome do usuário do MySQL
    private static String USER = "root";
    //Senha do banco de dados
    private static String PASS = "password";

    //Get e Set User
    public static String getUSER() {
        return USER;
    }
    public static void setUSER(String USER) {
        UsuarioMySQL.USER = USER;
    }
    
    //Get e Set PASS
    public static String getPASS() {
        return PASS;
    }
    public static void setPASS(String PASS) {
        UsuarioMySQL.PASS = PASS;
    }
    
    
    
}
