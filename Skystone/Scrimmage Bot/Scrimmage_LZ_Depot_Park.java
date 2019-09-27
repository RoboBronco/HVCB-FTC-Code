package FTC_2019_2020_Season;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="LZ-Depot-Park", group="Scrimmage")

/*
*
* @author Elicia & Camryn
*
*/
public class Scrimmage_LZ_Depot_Park extends LinearOpMode{
    
    RobotHardware robot = new RobotHardware();
    private ElapsedTime runtime = new ElapsedTime();
    
    @Override
    public void runOpMode(){
        
        robot.init(hardwareMap); 
        
        telemetry.addData("Status: ", "Waiting");
        telemetry.update();
        
        waitForStart();
        
        telemetry.addData("Status: ", "Running");
        telemetry.update();
        
        sleep(2000);
        
    }
    
}
