package FTC_2019_2020_Season;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.Math;

@Autonomous(name="BZ-Foundation-Park", group="Scrimmage")

/*
*
* Author Elicia & Camryn
*
*/
public class Scrimmage_BZ_Foundation_Park extends LinearOpMode {
    
    RobotHardware robot = new RobotHardware();
    private ElapsedTime runtime = new ElapsedTime();
    
    final int SQUARE = 0750;
    int allianceColor = 0;
    
    @Override
    public void runOpMode(){
        
        robot.init(hardwareMap); 
        
        while(allianceColor == 0){
           if(gamepad1.x){
                allianceColor = 1; //BLUE
                telemetry.addData("Status: ", "We are BLUE alliance");
                telemetry.update();
                sleep(1000);
            }else if(gamepad1.b){
                allianceColor = 2; //RED
                telemetry.addData("Status: ", "We are RED alliance");
                telemetry.update();
                sleep(1000);
            }
        }
        
        telemetry.addData("Status ", "Waiting");
        telemetry.update();
        
        waitForStart();
        
        telemetry.addData("Status ", "Running");
        telemetry.update();
        
        //Move forward 1.25 squares towards foundation
        robot.backward();
        sleep((int)Math.floor(SQUARE*1.25));
        robot.stop();
        sleep(5000);
        
        //Clamp onto foundation
        robot.hook.setPosition(1);
        sleep(2000);
        
        //Move backward w/ foundation 1.20 squares
        robot.forward();
        sleep((int)Math.floor(SQUARE*1.2));
        robot.stop();
        
        sleep(5000);
        
        //Unclamp foundation
        robot.hook.setPosition(-1);
        sleep(2000);
        
        //Strafe 1.5 squares to park according to color
        if(allianceColor == 1){ //Strafe left for BLUE
            robot.left();
            sleep((int)Math.floor(SQUARE*1.5));
            robot.stop();
        }else if(allianceColor == 2){ //Strafe right for RED
            robot.right();
            sleep((int)Math.floor(SQUARE*1.5));
            robot.stop();
        }
        

    }
        
}
