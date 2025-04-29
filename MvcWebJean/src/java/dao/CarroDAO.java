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

import vo.MarcaVO;

public class CarroDAO {
    public boolean gravar(MarcaVO p) {
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.estabeleceConexao();
            if (con != null) {
                PreparedStatement ps;
                String sql = "insert into veiculos (nome) values (?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, p.getNome());
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

    public ArrayList<MarcaVO> buscarPessoas() {
        PreparedStatement ps; // estrutura o sql
        ResultSet rs; //armazenará o resultado do bd
        Connection con; //conexão com o bd

        try {
            System.out.println("tentando fazer conexao");
            Conexao conexao = new Conexao();
            con = conexao.estabeleceConexao();
            if (con != null) {
                String sql = "select id, marca from veiculos";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                ArrayList<MarcaVO> lista = new ArrayList<>();
                while (rs.next()) {
                    //setar os valores dentro de um objeto (PessoaVO)
                    //adicionar este objeto a uma list
                    MarcaVO p = new MarcaVO();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("marca"));
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
        PreparedStatement ps; // estrutura o sql        
        Connection con; //conexão com o bd

        try {
            Conexao conexao = new Conexao();
            con = conexao.estabeleceConexao();
            if (con != null) {
                String sql = "delete from pessoas where id = ?";
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
