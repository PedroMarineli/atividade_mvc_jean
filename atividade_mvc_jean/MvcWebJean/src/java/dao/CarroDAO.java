package dao;

/**
 *
 * @author PEDRO_TI
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connection.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.CarroVO;

public class CarroDAO {
    public boolean gravar(CarroVO p) {
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.estabeleceConexao();
            if (con != null) {
                PreparedStatement ps;
                String sql = "insert into veiculos (marca, modelo, ano) values (?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, p.getMarca());
                ps.setString(2, p.getModelo());
                ps.setString(3, p.getAno());    
                int resultado = ps.executeUpdate(); //*
                conexao.fecharConexao(); //*
                return resultado != 0; //*                 
            } else {
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Exceção causada na inserção: " + erro);
            return false;
        }
    }

    public ArrayList<CarroVO> buscarPessoas() {
        PreparedStatement ps; 
        ResultSet rs; 
        Connection con;

        try {
            System.out.println("tentando fazer conexao");
            Conexao conexao = new Conexao();
            con = conexao.estabeleceConexao();
            if (con != null) {
                String sql = "select id, marca, modelo, ano from veiculos";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                ArrayList<CarroVO> lista = new ArrayList<>();
                while (rs.next()) {
                    CarroVO p = new CarroVO();
                    p.setId(rs.getInt("id"));
                    p.setMarca(rs.getString("marca"));
                    p.setModelo(rs.getString("modelo"));
                    p.setAno(rs.getString("ano"));
                    lista.add(p);
                }
                con.close();
                return lista;
            } else {
                return null;
            }
        } catch (SQLException erro) {
            System.err.print("Exceção gerada ao tentar buscar os dados: " + erro.getMessage());
            return null;
        }
    }

    public boolean excluir(int id) {
        PreparedStatement ps;       
        Connection con;

        try {
            Conexao conexao = new Conexao();
            con = conexao.estabeleceConexao();
            if (con != null) {
                String sql = "delete from veiculos where id = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                int resultado = ps.executeUpdate();
                con.close();        
                return resultado != 0;
            } else {
                return false;
            }
        } catch (SQLException erro) {
            return false;
        }
    }
}
