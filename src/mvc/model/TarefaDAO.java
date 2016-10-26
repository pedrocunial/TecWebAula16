package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TarefaDAO {

	private Connection connection = null;

	// DB
	public static final String DB_IP = "localhost";
	public static final String DB_NAME = "cagado";
	public static final String DB_LOGIN = "pedro";
	public static final String DB_PWD = "123456";

	public TarefaDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + DB_IP + "/" + DB_NAME,
					DB_LOGIN, DB_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionaTarefa(Tarefa tarefa) {
		adicionaDescricao(tarefa);
		adicionaFinalizado(tarefa);
		adicionaDataFinalizacao(tarefa);
	}

	public void adicionaFinalizado(Tarefa tarefa) {
		try {
			String sql = "INSERT INTO Tarefas (finalizado) values(?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, tarefa.isFinalizado());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void adicionaDataFinalizacao(Tarefa tarefa) {
		if (tarefa.isFinalizado()) {
			try {
				String sql = "INSERT INTO Tarefas (dataFinalizacao) values(?)";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setDate(2, new Date(
						tarefa.getDataFinalizacao().getTimeInMillis()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void adicionaDescricao(Tarefa tarefa) {
		try {
			String sql = "INSERT INTO Tarefas (descricao) values(?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Tarefa> getLista() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		try {
			String sql = "SELECT * FROM Tarefas";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				Calendar data = Calendar.getInstance();
				Date dataFinalizacao = rs.getDate("dataFinalizacao");
				if (dataFinalizacao != null) {
					data.setTime(dataFinalizacao);
					tarefa.setDataFinalizacao(data);
				}
				tarefas.add(tarefa);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tarefas;
	}

	public void remove(Tarefa tarefa) {
		try {
			String sql = "DELETE FROM Tarefas WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Tarefa buscaPorId(Long id) {
		Tarefa tarefa = new Tarefa();
		try {
			String sql = "SELECT * FROM Tarefas WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				Calendar data = Calendar.getInstance();
				Date dataFinalizado = rs.getDate("dataFinalizacao");
				if (dataFinalizado != null) {
					data.setTime(dataFinalizado);
					tarefa.setDataFinalizacao(data);
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tarefa;
	}

	public void altera(Tarefa tarefa) {
		try {
			String sql = "UPDATE Tarefas SET descricao=?, finalizado=?, " +
					"dataFinalizacao=? WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,  tarefa.getDescricao());
			stmt.setBoolean(2,  tarefa.isFinalizado());
			if (tarefa.getDataFinalizacao() != null) {
				stmt.setDate(3, new Date(tarefa.getDataFinalizacao()
						.getTimeInMillis()));
			} else {
				stmt.setDate(3, new Date(Calendar.getInstance()
						.getTimeInMillis()));
			}
			stmt.setLong(4, tarefa.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void finaliza(Long id) {
	        try {
	            String sql = "UPDATE Tarefas SET finalizado=?, "
	            		+ "dataFinalizacao=? " +
	            		"WHERE id=?";
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setBoolean(1, true);
	        stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
	        stmt.setLong(3, id);
	        stmt.executeUpdate();
	        stmt.close();
	    } catch(SQLException e) {System.out.println(e);}
	}

	public void close() {
	    try { connection.close();}
	    catch (SQLException e) {e.printStackTrace();}
	}

}
