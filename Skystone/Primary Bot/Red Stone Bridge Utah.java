package FTC_2019_2020_Season;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;

@Autonomous(name="Red Stone Bridge Utah", group="Auto")
public class Red_Stone_Bridge_Utah extends LinearOpMode {
 
 

 
     /* Declare OpMode members. */
     RobotHardware robot = new RobotHardware();
     private ElapsedTime runtime = new ElapsedTime();
     
    ColorSensor sensorColor0;
    ColorSensor sensorColor1;

  
 
     @Override
     public void runOpMode() throws InterruptedException{
      
      try  {
      
      
     /*
     * Initialize the drive system variables.
     * The init() method of the hardware class does all the work here
     */
     robot.init(hardwareMap);
     
       // get a reference to the color sensor.
        sensorColor0 = hardwareMap.get(ColorSensor.class, "sensorColor0");
        sensorColor1 = hardwareMap.get(ColorSensor.class, "sensorColor1");
        // get a reference to the distance sensor that shares the same name.
       // sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues0[] = {0F, 0F, 0F};
        float hsvValues1[] = {0F, 0F, 0F};
        // values is a reference to the hsvValues array.
       

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        char skystoneLocation = 'r';
        boolean position = false;


        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        telemetry.addData("Mode", "calibrating2...");
        telemetry.update();

        while (!isStopRequested() && !robot.imu.isGyroCalibrated())
        {
            Thread.sleep(50);
            idle();
        }


     
     // Wait for the game to start (driver presses PLAY)
        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();
        waitForStart();
        
 
            if (opModeIsActive()) {
             
             
             ///////////////////////////////////////////////////////////////////////////////////////////////////////
             /////////////////////////// Get to Block here/////////////////////////////////////////////////////////
             /////////////////////////////////////////////////////////////////////////////////////////////////////
             
             //robot.leftByEncoder(0.2 , -600, this);
             robot.leftByEncoder(0.75 , -7700, this);
             robot.leftByEncoder(0.2 , -874, this);
             Thread.sleep(100);
             
              
             while (position != true){
            Color.RGBToHSV((int) (sensorColor0.red() * SCALE_FACTOR),
                    (int) (sensorColor0.green() * SCALE_FACTOR),
                    (int) (sensorColor0.blue() * SCALE_FACTOR),
                    hsvValues0);
            Color.RGBToHSV((int) (sensorColor1.red() * SCALE_FACTOR),
                    (int) (sensorColor1.green() * SCALE_FACTOR),
                    (int) (sensorColor1.blue() * SCALE_FACTOR),
                    hsvValues1);
                    
            telemetry.addData("Hue for color sensor 0", hsvValues0[0]);
            telemetry.addData("----First color Sensor ends here" , "----");
            telemetry.addData("Hue for sensor 1", hsvValues1[0]);
            telemetry.addData("----Second color Sensor ends here" , "----");
            telemetry.update();
            
            Thread.sleep(100);
            
            
            ////////////////////////////
            ////////////////////////////
            ///////////LEFT/////////////
            ////////////////////////////
            ////////////////////////////
            
            
            if (hsvValues1[0] > 105 && hsvValues0[0] < 105){
                telemetry.addData("Skystone is Left " , "yeet");
                    
                    // //Back up
                    // robot.backwardByEncoder(0.2, -200, this);
                    // Thread.sleep(50);
                    
                    // //Move up to stones
                    // robot.leftByEncoder(0.25, -600, this);
                    // Thread.sleep(50);
                    
                    //Grab Skystone
                    robot.blockBlue.setPosition(0.1);
                    sleep(600);
                    
                    //Secure Skystone
                    robot.grabBlue.setPosition(0.7);
                    sleep(750);
                    
                    //Lift Skystone
                    robot.blockBlue.setPosition(0.45);
                    
                    
                    // //Rotate slightly away from stones
                    // robot.rotate(-2, 0.2, this);//was-6 degrees
                    // Thread.sleep(100);
                    
                    //Move away from stones 
                    robot.rightByEncoder(0.4, 1500, this);
                    Thread.sleep(50);
                    
                    //Under Bridge
                    robot.forwardByEncoder(0.6 , 16388, this);
                  
                    robot.forwardByEncoder(0.2 , 600, this);
                    Thread.sleep(50);
                    
                    // robot.leftByEncoder(0.3 , -1000, this);
                    // Thread.sleep(50);
                    
                    robot.leftByEncoder(0.3 , -1500, this);
                    Thread.sleep(50);
                    
                    //Lower Skystone
                    robot.blockBlue.setPosition(0.45); // 0.3
                    Thread.sleep(500);
                    
                    //Release Skystone
                    robot.grabBlue.setPosition(0.3);
                    Thread.sleep(250);
                    
                    //Lift Arm
                    robot.blockBlue.setPosition(0.48); // .48
                    Thread.sleep(500);
                    
                    robot.rightByEncoder(0.3 , 1500, this);
                    Thread.sleep(50);
                    
                    robot.rotate(-1, 0.3 , this);
                    sleep(50);
                    
                    //Back to stones
                    robot.backwardByEncoder(0.8 , -22750, this);
                    robot.backwardByEncoder(0.2 , -1600, this);
                    Thread.sleep(50);
                    
                    robot.backward(0.2);
                    Thread.sleep(100);
                    robot.stop();
                    
                    //Against stones
                    robot.leftByEncoder(0.5 , -2700, this);
                    Thread.sleep(50);
              
                    //MORE AGAINST STONES!
                    robot.leftByEncoder(0.2 , -600, this);
                    Thread.sleep(50);
                    
                    //MORE Away STONES!
                    robot.rightByEncoder(0.2 , 300, this);
                    Thread.sleep(50);
                    
                    // //AGAINST THE WALL AGAIN
                    // robot.backward(); // was backward
                    // Thread.sleep(100);
                    // robot.stop();
                    
                    robot.backward(0.3);
                    sleep(200);  
                    robot.stop();
                    
                    //Grab Skystone
                    robot.blockBlue.setPosition(0.1);
                    sleep(600);
                    
                    //Secure Skystone
                    robot.grabBlue.setPosition(0.7);
                    sleep(1000);
                    
                    //Lift Skystone
                    robot.blockBlue.setPosition(0.45);
                    
                    //move away from stones
                    robot.rightByEncoder(0.5 , 3300, this);
                    Thread.sleep(50);
                    
                    
                    
                    //Under bridge
                    robot.forwardByEncoder(0.8 , 31700, this);
                    Thread.sleep(50);
                    
                    //Rotate to foundation
                    robot.rotate(-82, 0.3, this);
                    sleep(50);
                    
                    //Backup to foundation
                    robot.backwardByEncoder(0.4 , -3500, this);
                    Thread.sleep(50);
                    
                    // Grab Foundation
                    robot.hook0.setPosition(0.3);// was -0.4 //was -0.3   ////////////////////left
                    robot.hook1.setPosition(0.9);//was 0.8 //was 0.45   ///////////////////right
                    Thread.sleep(750);
                    
                    //Move foundation
                    robot.forwardByEncoder(0.5 , 7500, this);
                    Thread.sleep(50);
                    
                    //Reset angle for more rotations
                    robot.resetAngle();
                    
                    //Rotate with foundation
                    robot.rotate(-82, 0.5, this);
                    sleep(50);
                    
                    // robot.backwardByEncoder(0.5 , -2400, this);
                    // Thread.sleep(50);
                    
                    //Release Foundation
                    robot.hook0.setPosition(0.95);//was -0.6
                    robot.hook1.setPosition(0.25);//was 0.6
                    Thread.sleep(1000);
                    
                    //Move away from wall and to bridge
                    robot.rightByEncoder(0.5 , 5400, this);
                    Thread.sleep(50);
                    
                    //Release Skystone
                    robot.grabBlue.setPosition(0.1);
                    Thread.sleep(250);
                    
                    robot.backward(0.3);
                    Thread.sleep(400);
                    robot.stop();
                    
                    //Under bridge
                    robot.forwardByEncoder(0.5 , 10000, this);
                    Thread.sleep(50);
                    
                    skystoneLocation = 'l';
                    position = true;
                    }   
                    
                    
          ////////////////////////////////////////////////////
          ////////////middle///////////////////////////////////
          ////////////////////////////////////////////////////
                    
                    
                    else if (hsvValues0[0] > 105 && hsvValues1[0] < 105){ 
                    telemetry.addData("Skystone is Middle " , "yeet");
                    
                    robot.backwardByEncoder(0.25 , -300, this); // was -400
                    Thread.sleep(50);
                    
                    // robot.forwardByEncoder(0.25, 600, this);
                    // Thread.sleep(50);
                    
                    
                     //Grab Skystone
                    robot.blockRed.setPosition(1);
                    sleep(600);
                    
                    //Secure Skystone
                    robot.grabRed.setPosition(0.1);
                    sleep(750);
                    
                    //Lift Skystone
                    robot.blockRed.setPosition(0.56);
                    
                    //Move away from stones 
                    robot.rightByEncoder(0.4, 1500, this);
                    Thread.sleep(50);
                
                    
                    // //Rotation away from stones
                    //  robot.rotate(-2, 0.2, this); // was -6
                    //  Thread.sleep(100);
                    
                    //Under bridge
                    robot.forwardByEncoder(0.6 , 14688, this);
                    Thread.sleep(50);
                    
                    //Move left past bridge to avoid collison 
                    robot.leftByEncoder(0.3 , -1500, this);
                    Thread.sleep(50);
                    
                    //Drop stone
                    robot.blockRed.setPosition(1);
                    Thread.sleep(500);
                    
                    //Release stone
                    robot.grabRed.setPosition(0.7);
                    Thread.sleep(750);
                    
                    //Lift Arm
                    robot.blockRed.setPosition(0.45);
                    Thread.sleep(200);
                    
                    robot.rightByEncoder(0.3 , 1500, this);
                    Thread.sleep(50);
                    
                    robot.resetAngle();
                    robot.rotate(-1, 0.3 , this);
                    sleep(50);
                    
                    //Move back under bridge
                    robot.backwardByEncoder(0.6 , -20808, this);
                    Thread.sleep(50);
                    
                    //Hit the wall
                    robot.backward(0.2); // was backward
                    Thread.sleep(500);
                    
                    //Push into stones
                    robot.leftByEncoder(0.35 , -3075, this);
                    Thread.sleep(50);
                    
                    // //Against the wall again
                    // robot.backward(); // was backward
                    // Thread.sleep(100);
                    // robot.stop();
                    
                    robot.rightByEncoder(0.3 , 300, this);
                    Thread.sleep(50);
                    
                     //Grab Skystone
                    robot.blockRed.setPosition(1);
                    sleep(500);
                    
                    //Secure Skystone
                    robot.grabRed.setPosition(0.1);
                    sleep(600);
                    
                    //Lift Skystone
                    robot.blockRed.setPosition(0.56);
                    sleep(100);
                    
                    //Move away from stones
                    robot.rightByEncoder(0.3 , 2100, this);
                    Thread.sleep(50);
                    
                    robot.resetAngle();
                    robot.rotate(-2, 0.3 , this);
                    sleep(50);
                    
                    //Under bridge
                    robot.forwardByEncoder(0.8 , 30500, this);
                    Thread.sleep(50);
                    
                     //Rotate to foundation
                    robot.rotate(-82, 0.3, this);
                    sleep(50);
                    
                    //Backup to foundation
                    robot.backwardByEncoder(0.4 , -4700, this);
                    Thread.sleep(50);
                    
                    // Grab Foundation
                    robot.hook0.setPosition(0.3);// was -0.4 //was -0.3   ////////////////////left
                    robot.hook1.setPosition(0.9);//was 0.8 //was 0.45   ///////////////////right
                    Thread.sleep(750);
                    
                    //Move foundation
                    robot.forwardByEncoder(0.5 , 7500, this);
                    Thread.sleep(50);
                    
                    //Reset angle for more rotations
                    robot.resetAngle();
                    
                    //Rotate with foundation
                    robot.rotate(-82, 0.5, this);
                    sleep(50);
                    
                    // robot.backwardByEncoder(0.5 , -2400, this);
                    // Thread.sleep(50);
                    
                    //Release Foundation
                    robot.hook0.setPosition(0.95);//was -0.6
                    robot.hook1.setPosition(0.25);//was 0.6
                    Thread.sleep(1000);
                    
                    //Move away from wall and to bridge
                    robot.rightByEncoder(0.9 , 4500, this);
                    Thread.sleep(50);
                    
                    //Release stone
                    robot.grabRed.setPosition(0.7);
                    Thread.sleep(750);
                    
                    robot.backward(0.3);
                    Thread.sleep(400);
                    robot.stop();
                    
                    //Under bridge
                    robot.forwardByEncoder(0.9 , 10900, this);
                    Thread.sleep(50);
                  
                        
                        robot.stop();
                    
                    skystoneLocation = 'm';
                    position = true;
                    }   else {
                     
                     /////////////////////////////////////////
                    ///////////////////right/////////////////
                    //////////////////////////////////////////
                    telemetry.addData("Skystone is Right " , "yeet");
                                       
                    robot.backwardByEncoder(0.3, -2000, this);
                    Thread.sleep(50);

                    // robot.leftByEncoder(0.3, -1200, this);//was -600
                    // Thread.sleep(50);
                    
                    
                   
                    //Grab Skystone
                    robot.blockBlue.setPosition(0.1);
                    sleep(600);
                    
                    //Secure Skystone
                    robot.grabBlue.setPosition(0.7);
                    sleep(750);
                    
                    //Lift Skystone
                    robot.blockBlue.setPosition(0.45);
                    

                    // //Rotate away from stones
                    // robot.rotate(-2, 0.2, this); // was -6
                    // Thread.sleep(100);
                     
                     //Move away from stones 
                    robot.rightByEncoder(0.4, 2400, this);
                    Thread.sleep(50);
                    
                    //Go under bridge
                    robot.forwardByEncoder(0.6 , 18500, this);
                    Thread.sleep(50);
                    
                     robot.leftByEncoder(0.3 , -1500, this);
                    Thread.sleep(50);
                    
                    // //Lower Skystone
                    // robot.blockBlue.setPosition(0.1);
                    // Thread.sleep(500);
                    
                    //Release Skystone
                    robot.grabBlue.setPosition(0.3);
                    Thread.sleep(250);
                    
                    //Lift Arm
                    robot.blockBlue.setPosition(0.48);
                    //Thread.sleep(200);
                    
                    robot.rightByEncoder(0.3 , 600, this);
                    Thread.sleep(50);
                    
                    //Back to stones
                    robot.backwardByEncoder(0.55 , -10500, this);
                    Thread.sleep(50);
                    
                    robot.leftByEncoder(0.3 , -3168, this);
                    Thread.sleep(50);
                    
                    
                    robot.rightByEncoder(0.3 , 280, this);
                    Thread.sleep(50);
             
                    
                    //Grab Skystone
                    robot.blockBlue.setPosition(0.1);
                    sleep(600);
                    
                    //Secure Skystone
                    robot.grabBlue.setPosition(0.7);
                    sleep(750);
                    
                    //Lift Skystone
                    robot.blockBlue.setPosition(0.45);
                    
                    //Move away 
                    robot.rightByEncoder(0.35 , 2700, this); // was 3100
                    Thread.sleep(50);
                    
                    robot.forwardByEncoder(0.5 , 19000, this);
                    Thread.sleep(50);
                    
                     //Rotate to foundation
                    robot.rotate(-87, 0.3, this);
                    sleep(50);
                    
                    //Backup to foundation
                    robot.backwardByEncoder(0.4 , -3500, this);
                    Thread.sleep(50);
                    
                    // Grab Foundation
                    robot.hook0.setPosition(0.3);// was -0.4 //was -0.3   ////////////////////left
                    robot.hook1.setPosition(0.9);//was 0.8 //was 0.45   ///////////////////right
                    Thread.sleep(750);
                    
                    //Move foundation
                    robot.forwardByEncoder(0.5 , 7500, this);
                    Thread.sleep(50);
                    
                    //Reset angle for more rotations
                    robot.resetAngle();
                    
                    //Rotate with foundation
                    robot.rotate(-82, 0.5, this);
                    sleep(50);
                    
                    // robot.backwardByEncoder(0.5 , -2400, this);
                    // Thread.sleep(50);
                    
                    //Release Foundation
                    robot.hook0.setPosition(0.95);//was -0.6
                    robot.hook1.setPosition(0.25);//was 0.6
                    Thread.sleep(1000);
                    
                    //Move away from wall and to bridge
                    robot.rightByEncoder(0.9 , 3000, this);
                    Thread.sleep(50);
                    
                    //Release Skystone
                    robot.grabBlue.setPosition(0.1);
                    Thread.sleep(250);
                    
                    robot.backward(0.3);
                    Thread.sleep(600);
                    robot.stop();
                    
                    //Under bridge
                    robot.forwardByEncoder(0.9 , 10900, this);
                    Thread.sleep(50);
                  
                        
                        
                    skystoneLocation = 'r';
                    position = true;
                    }
                    
                    Thread.sleep(500);
                    
                    
                    telemetry.update();
                    
                    
            }
         }
         
       
      } catch(InterruptedException e){
 telemetry.addLine("Hey Caught Interruption");
 telemetry.update();
 robot.stop();
//  robot.ssExtend.setPower(0);
//  robot.ssScrew.setPower(0);
 Thread.sleep(500);
}
       
     }
  }
