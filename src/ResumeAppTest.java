
public class ResumeAppTest {

	public static void main(String[] args) {
		
		
		int thisPersonID;
		
	
		Person me=new Person("Ram","Bahadur","Ram@montgomerycollege.edu");
		me.addMe();
		System.out.println("Your full Name is: " +me.getFname()+" " +me.getLname()+"  and your "
				+ "email is "+me.getEmail());
		System.out.print(me.getPersonid());
	
		
		
		
		Job job=new Job();
		job.setEmployer("Google");
		job.setjTitle("Engineer");
		job.addJobtoDB(me.getPersonid());  // must add job to DB before Duty because that's how duty table is setup
		job.addDuty("dancing");
		job.addDuty("singing");
	
		
	
		
		me.addJob(job);
		
		
		System.out.println("Name:"+me.getFname()+" "+me.getLname());
		System.out.println("Email:"+me.getEmail());
		System.out.println("");
		
		System.out.println("Job Experience");
		
		me.JobList();
		
		
		
		
		
		

	}

}
