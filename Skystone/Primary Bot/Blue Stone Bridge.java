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

@Autonomous(name="Blue Stone Bridge", group="Auto")
public class Blue_Stone_Bridge extends LinearOpMode {
 
 

 
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
             
             robot.ssTilt.setPosition(0.65);
             Thread.sleep(1000);
             
            // robot.backwardByEncoder(0.25 , -700);
             //Thread.sleep(50);
             
             robot.leftByEncoder(0.6 , -6700);
             robot.leftByEncoder(0.2 , -1874);
             Thread.sleep(50);
             
              
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
            
            if (hsvValues1[0] > 105 && hsvValues0[0] < 105){
                
                /////////////////////////////////////////////////////////////////////////////
                ////////////////// This Is Middle     5     2   !!!!!! ///////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////
                    
                     robot.leftByEncoder(0.2, -600);
                    Thread.sleep(50);
                    
                    robot.blockBlue.setPosition(0.1);
                    Thread.sleep(750);
                    
                    robot.rotate(-6, 0.2);
                     Thread.sleep(100);
                     
                    robot.rightByEncoder(0.5, 2100);
                    robot.rightByEncoder(0.2, 300);
                    Thread.sleep(50);
                    
                    robot.backwardByEncoder(0.3 , -15688);
                    Thread.sleep(50);
                    
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);

                    robot.forwardByEncoder(0.3 , 11000);
                    Thread.sleep(50);
                    
                    robot.ssTilt.setPosition(0);
                    
                    robot.forwardByEncoder(0.3 , 10808);
                    Thread.sleep(50);
                    
                    robot.forward(0.2);
                    Thread.sleep(750);
                    
                    robot.leftByEncoder(0.2 , -3475);
                    Thread.sleep(50);
                    
                    robot.forward();
                    Thread.sleep(100);
                    robot.stop();
                    
                    robot.blockBlue.setPosition(0.1);
                    Thread.sleep(1250);
                    
                    robot.rightByEncoder(0.3 , 3000);
                    Thread.sleep(50);
                    
                    robot.backwardByEncoder(0.3 , -11000);
                    Thread.sleep(50);
                    
                    robot.ssTilt.setPosition(0.65); 
                    
                    robot.backwardByEncoder(0.3 , -11808);
                    Thread.sleep(50);
                    
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);
                    
                    robot.forwardByEncoder(0.3 , 3400);
                    Thread.sleep(50);
                        
                        robot.stop();
                        
                        Thread.sleep(30000);
                        
                    
                    
            }   else if (hsvValues0[0] > 105 && hsvValues1[0] < 105){ 

                    //////////////////////////////////////////////////////////////////
                    ///////////// skystone is      1     4  /////////////////////////
                    ////////////////////////////////////////////////////////////////
                    
                    
                    // robot.forwardByEncoder(0.2 , 400);
                    // Thread.sleep(50);
                    
                    
                    robot.leftByEncoder(0.2, -600);
                    Thread.sleep(50);

                     robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);

                    robot.rightByEncoder(0.5, 2100);
                    robot.rightByEncoder(0.2, 300);
                    Thread.sleep(50);
                    
                    robot.rotate(1, 0.2);
                    Thread.sleep(100);
                    
                    robot.backwardByEncoder(0.3 , -15988);
                    Thread.sleep(50);
                    
                    robot.blockRed.setPosition(0.55);
                    Thread.sleep(750);

                   robot.forwardByEncoder(0.3 , 11300);
                    Thread.sleep(50);
                    
                    robot.ssTilt.setPosition(0);
                    
                    robot.forwardByEncoder(0.3 , 11108);
                    Thread.sleep(50);
                    
                    robot.forward(0.2);
                    Thread.sleep(750);
                    
                    robot.leftByEncoder(0.3 , -3475);
                    Thread.sleep(50);
                    
                    robot.forward();
                    Thread.sleep(100);
                    robot.stop();
                    
                    robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);
                    
                    robot.rightByEncoder(0.3 , 3400);
                    Thread.sleep(50);
                    
                    robot.backwardByEncoder(0.3 , -15300);
                    Thread.sleep(50);

                    robot.ssTilt.setPosition(0.65);
                    
                    robot.backwardByEncoder(0.3 , -8600);
                    Thread.sleep(50);
                    
                   robot.blockRed.setPosition(0.55);
                    Thread.sleep(750);

                    robot.forwardByEncoder(0.3 , 4300);
                    Thread.sleep(50);
                        
                        robot.stop();
                        
                     Thread.sleep(30000);

                    
            }   else {
                    
                    //////////////////////////////////////////////////////////////////////////////////////////////
                    ////////////  Skystone position 3  6 /////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////
                                       
                    robot.forwardByEncoder(0.25, 1800);
                    Thread.sleep(50);
                                       
                    robot.leftByEncoder(0.2, -600);
                    Thread.sleep(50);
                    
                    robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);
                    
                    robot.rotate(1, 0.2);
                     Thread.sleep(100);
                     
                    robot.rightByEncoder(0.5, 2100);
                    robot.rightByEncoder(0.2, 300);
                    Thread.sleep(50);
                    
                    robot.backwardByEncoder(0.3 , -17000);
                    Thread.sleep(50);
                    
                    robot.blockRed.setPosition(0.55);
                    Thread.sleep(750);

                    robot.forwardByEncoder(0.3 , 9800);
                    Thread.sleep(50);
                    
                    robot.leftByEncoder(0.3 , -2568);
                    Thread.sleep(50);
                    
                    robot.left(0.2);
                    Thread.sleep(400);
                    robot.stop();
                    
                    robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);
                    
                    robot.rightByEncoder(0.3 , 3100);
                    Thread.sleep(50);
                    
                    robot.backwardByEncoder(0.3 , -11000);
                    Thread.sleep(50);
                    
                    robot.blockRed.setPosition(0.55);
                    Thread.sleep(750);
                    
                    robot.forwardByEncoder(0.3 , 4000);
                    Thread.sleep(50);
                        
                        robot.stop();
                        
                     Thread.sleep(30000);
                        
                  
            }
            
            Thread.sleep(500);
            


            telemetry.update();
            

            }
         }
         
       
      } catch(InterruptedException e){
 telemetry.addLine("Hey Caught Interruption");
 telemetry.update();
 robot.stop();
 robot.ssExtend.setPower(0);
 robot.ssScrew.setPower(0);
 Thread.sleep(500);
}
       
     }
  }

    
