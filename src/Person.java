import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.*;

public class Person {




	private String fname;
	private String lname;
	private String email;
	private ArrayList<Job> myJobList;
	private ArrayList<Education> myEduList;
	private ArrayList<Skills> mySkillList;
	private int personid;

	public Person(String pfname, String plname, String pemail) {
		this.fname=pfname;
		this.lname=plname;
		this.email=pemail;
		myJobList=new ArrayList<Job>();
		myEduList=new ArrayList<Education>();
		mySkillList=new ArrayList<Skills>();

	}

	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}



	public String getFname() {
		return fname;
	}


	public String getLname() {
		return lname;
	}


	public String getEmail() {
		return email;
	}

	public void addJob(Job jobToAdd){
		
		myJobList.add(jobToAdd);




	}
	
	public void JobList(){
		
		for (Job job: myJobList){
			System.out.println(job.getJobId());
			System.out.println(job.getjTitle());
			System.out.println(job.getEmployer());
			
			for (String duty: job.getJobDuties()){
				
				System.out.println(duty);
			}
			
			
			
		}
	}

	public int  addMe(){

		Connection connect = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		String sql="Insert into person (fname,lname,email) values(?,?,?)";
		String idsql="SELECT id FROM person where email=?";
		

		try{


			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/resume?user=root&password=password");
			stmt=connect.prepareStatement(sql);
			stmt.setString(1, this.fname);
			stmt.setString(2, this.lname);
			stmt.setString(3, this.email);
			stmt.executeUpdate();

			stmt=connect.prepareStatement(idsql);
			stmt.setString(1,this.email);
			rs=stmt.executeQuery();

			rs.next();

			this.setPersonid(rs.getInt("id"));




		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		finally{

		}

		System.out.print("You've saved data tot the database");
		return 0;



	}

}
