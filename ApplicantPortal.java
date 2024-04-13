import java.util.Scanner;
/**Serves as an interface for user interaction with the 
 * applicant management system. 
 */
 
public class ApplicantPortal
{
    private ApplicantManagement applicantManagement;
    private Scanner scanner;
    
	public ApplicantPortal(ApplicantManagement applicantManagement) {
	    this.applicantManagement = applicantManagement;
	    this.scanner = new Scanner(System.in);
	}
	    
	
	public void manageApplicant() {
	    boolean run = true;
	    
	    while (run) {
	        System.out.println("--- Applicant Portal ---");
	        System.out.println("1. Create Applicant");
	        System.out.println("2. Search Applicant"); 
	        System.out.println("3. Update Applicant");
	        System.out.println("4. Delete Applicant");
	        System.out.println("5. Review Applicant");
	        System.out.println("6. Return to Main Menu");
	        int userChoice = getInput();
	        
	        switch (userChoice) {
	           case 1:
	               createApplicant();
	               break;
	           case 2: 
	               readApplicant();
	               break;
	           case 3: 
	               updateApplicant();
	               break;
	           case 4:
	               deleteApplicant();
	               break;
	           case 5:
	               reviewApplicant();
	               break;
	           case 6:
	               System.out.println("Returning to main menu...");
	               run = false;
	               break;
	           default:
	               System.out.println("Invalid choice. Please enter a valid option.");
	            
	        }
	    }
	}
	
	/*Creates a new applicant*/
	public void createApplicant() {
	    System.out.println("Enter applicant name: ");
	    String name = scanner.nextLine();
	    System.out.println("Enter applicant address: ");
	    String address = scanner.nextLine();
	    System.out.println("Enter applicant phone Number: ");
	    String phoneNumber = scanner.nextLine();
	    System.out.println("Enter applicant email: ");
	    String email = scanner.nextLine();
	    Applicant newApplicant = new Applicant(name, address, phoneNumber, email);
	    applicantManagement.addApplicant(newApplicant);
	    System.out.println("Applicant created succesfully.");
	}
	
	/*Allows the user to search for the applicant*/
	public void readApplicant() {
	    System.out.println("Enter applicant name to search for: ");
	    String searchName = scanner.nextLine();
	    Applicant foundApplicant = applicantManagement.getApplicantByName(searchName);
	    if (foundApplicant != null) {
	        System.out.println("Applicant found: ");
	        System.out.println(foundApplicant);
	    }
	    else {
	        System.out.println("Applicant not found");
	    }
	    
	}
	
	/*Allows user to update applicant information*/
	public void updateApplicant() {
	    System.out.println("Enter applicant name to update: ");
	    String searchName = scanner.nextLine();
	    Applicant foundApplicant = applicantManagement.getApplicantByName(searchName);
	    if (foundApplicant != null) {
	        boolean continueUpdating = true;
	        while (continueUpdating) {
	            System.out.println("Choose what you wanted to update.");
	            System.out.println("1. Name");
	            System.out.println("2. Address");
	            System.out.println("3. Phone Number");
	            System.out.println("4. Email");
	            System.out.println("5. Done Updating");
	            
	            int userChoice = getInput();
	        
	            switch(userChoice){
	            case 1:
	                System.out.print("Enter new name: ");
	                foundApplicant.setName(scanner.nextLine());
	                break;
	            case 2:
	                System.out.print("Enter new address: ");
	                foundApplicant.setAddress(scanner.nextLine());
	                break;
	            case 3:
	                System.out.print("Enter new phone number: ");
	                foundApplicant.setPhoneNumber(scanner.nextLine());
	                break;
	            case 4:
	                System.out.print("Enter new email: ");
	                foundApplicant.setEmail(scanner.nextLine());
	                break;
	            case 5:
	                continueUpdating = false;
	                break;
	            default:
	                System.out.println("Invalid choice. No changes made.");
	            }
	        }
	        System.out.println("Applicant updated successfully.");
	    } else {
	        System.out.println("Applicant not found.");
	    }
	    
	}
	
	/*Deletes an exisiting applicant*/
	public void deleteApplicant() {
	    System.out.print("Enter applicant name to delete: ");
	    String searchName = scanner.nextLine();
	    Applicant foundApplicant = applicantManagement.getApplicantByName(searchName);
	    if (foundApplicant != null) {
	        applicantManagement.deleteApplicant(foundApplicant);
	        System.out.println("Applicant deleted successfully.");
	    } else {
	        System.out.println("Applicant not found.");
	    }
	    
	}
	
	/**Allows the user to review the applicant.
	 * If approved, applicant information goes to the employee portal.
	 */
	public void reviewApplicant() {
	    System.out.println("Enter applicant name to review: ");
	    String searchName = scanner.nextLine();
	    Applicant foundApplicant = applicantManagement.getApplicantByName(searchName);
	    if (foundApplicant != null) {
	        System.out.println("Would you like to approve or reject this applicant?");
	        System.out.println("1. Approve");
	        System.out.println("2. Reject");
	        int choice = getInput();
	        
	        switch (choice) {
	            case 1:
	                foundApplicant.setApproved(true);
	                System.out.println("Applicant approved.");
	                
	                applicantManagement.approveAndTransferApplicant(foundApplicant.getName());
	                break;
	            case 2: 
	                foundApplicant.setApproved(false);
	                System.out.println("Applicant " + searchName + " rejected.");
	                break;
	            default:
	                System.out.println("Invalid choice. No changes made.");
	        }
	        System.out.println("Review process completed.");
	        manageApplicant();
	    } else {
	        System.out.println("Applicant not found.");
	    }
	}
	
	/*public void reviewAndAddApplicant(Applicant applicant) {
	    double DEFAULT_SALARY = 40000;
	    
	    if(applicant != null && applicant.isApproved()) {
	        //Convert the list of applicants to an array
	        List<Applicant> approvedApplicantList = new ArrayList<>();
	        approvedApplicantsList.add(applicant);
	        Applicant[] approvedApplicantArray = approvedApplicantList.toArray(new Applicant[0]);
	        
	        //Iterates over the array of approved applicants and add them as employees
	        for(Applicant approvedApplicant : approvedApplicantsArray) {
	            Employee newEmployee = new Employee(approvedApplicant.getName(), approvedApplicant.getAddress(), 
	            approvedApplicant.getEmail(), DEFAULT_SALARY);
	            
	            //Add the new employee to the employeeService
	            employeeService.addEmployee(newEmployee);
	        }
	        System.out.println("Applicant " + applicant.getName() + " has been hired as an employee");
	        //Employee newEmployee = new Employee (applicant.getTemporaryId(), applicant.getName(), applicant.getAddress(), applicant.getEmail(), DEFAULT_SALARY);
	    
	    } else {
	        System.out.println("Invalid applicant.");
	    }
	}*/
	
	private int getInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
	
}
