package Modelo;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ConsultaClientes extends Conexion {

    public boolean registrar(Clientes cli) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO producto(ruccliente, nombres, direccion, telefono, estado) VALUES (?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getRucCliente());
            ps.setString(2, cli.getNombres());
            ps.setString(3, cli.getDireccion());
            ps.setString(4, cli.getEstado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
                return false;
            }
        }
    }
    
    public boolean modificar(Clientes cli) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE producto SET ruccliente=?, nombres=?, direccion=?, telefono=?, estado=? WHERE idclientes=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getRucCliente());
            ps.setString(2, cli.getNombres());
            ps.setString(3, cli.getDireccion());
            ps.setString(4, cli.getEstado());
            ps.setInt(5, cli.getIdclientes());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
                return false;
            }
        }
    }
    
    public boolean buscar(Clientes cli) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM producto WHERE ruccliente=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getRucCliente()); 
            ps.executeQuery();
            
            if(rs.next()){
                cli.setIdclientes(Integer.parseInt(rs.getString("idclientes")));
                cli.setRuccliente(rs.getString("ruccliente"));
                cli.setNombres(rs.getString("nombres"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setEstado(rs.getString("estado"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
                return false;
            }
        }
    }
}
