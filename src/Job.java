import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Job {

	private String jTitle;
	private String employer;
	LocalDateTime sdate;
	LocalDateTime edate;
	private int personId;
	

	private int jobId;
	private ArrayList <String> jduties;



	public Job(){
		jduties=new ArrayList<String>();

	}
	
	public void addDuty(String dutyString,int PersonID){
		
		//Get the relevant person ID
		//Get the relevant JobID
		String insertSQL= "insert into jobduties(jobid,jobduty) values (?,?)";
		this.jduties.add(dutyString+this.jobId);
		
	}
	
	public int getJobId() {
		return jobId;
	}


	public void setJobId(int jobId) {
		this.jobId = jobId;
	}


	
	public ArrayList<String> getJobDuties(){
		
		return jduties;
	}
	
	public void addDuty(String dutyString){
		this.jduties.add(dutyString);


	}

	public String getjTitle() {
		return jTitle;
	}
	public void setjTitle(String jTitle) {
		this.jTitle = jTitle;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public LocalDateTime getSdate() {
		return sdate;
	}
	public void setSdate(LocalDateTime sdate) {
		this.sdate = sdate;
	}
	public LocalDateTime getEdate() {
		return edate;
	}
	public void setEdate(LocalDateTime edate) {
		this.edate = edate;
	}


	public void addJobtoDB(int personId){


		Connection connect = null;
		PreparedStatement stmt = null;
		String addJobSql="Insert into job (jobtitle,employer,personId) values (?,?,?)";
		String addDutiesSql="Insert into job (jobtitle,employer,personId) values (?,?,?)";
		String getCurrentJobID="select id from job where company=? and jobtitle=? and personid=?";
		ResultSet rs=null;
		
		
		String dutiesToAdd="";
		
		for(String duty : jduties)
			
		{
			dutiesToAdd+=duty;
			
		}

		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/resume?user=root&password=password");

			stmt=connect.prepareStatement(addJobSql);



			stmt.setString(1,this.jTitle);
			stmt.setString(2,this.employer);
			stmt.setInt(3,personId);
		
			stmt.executeUpdate();
			
			stmt=connect.prepareStatement(getCurrentJobID);
			stmt.setString(1, this.getEmployer());
			stmt.setString(1, this.getjTitle());
			stmt.setInt(3, personId);
			
			rs=stmt.executeQuery();
			
			rs.next();
			this.setJobId(rs.getInt("id"));
			
	
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		
		
		}
	

	}





