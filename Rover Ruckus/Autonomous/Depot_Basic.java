
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Basic Depot", group="Auto")
public class Depot_Basic extends LinearOpMode {

    /* Declare OpMode members. */
   RoverHardware robot           = new RoverHardware();
   private ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status: ", "Ready to Deploy!");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////// LANDING //////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        //WORKS
        // Lower robot to level with playing field
        /////////CORRECT DIRECTION (UP)//////////////
        robot.lift.setPower(-1);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 3.4)){
            telemetry.addData("Unfolding:", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.lift.setPower(0);

        sleep(1000);
        
        //WORKS
        // Detach from lander
        robot.right();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.1)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }robot.stop();
        
        sleep(1000);
        
          robot.backward();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.03)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }robot.stop();
        
        sleep(1000);
        
        robot.forward();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.57)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
            robot.backward();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.02)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
         sleep (1000);
        
        robot.right();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.52)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
            robot.left();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.02)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
        sleep (1000);
        
      // // add color sensor servo
        
        
        /////////////////////////////////////
        //sampling logic/////////////////////
        /////////////////////////////////////
    
    
        // from postion 1 to 2
         robot.left();
         runtime.reset();
         while(opModeIsActive() && (runtime.seconds() <= 0.65)){
             //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
         }
         robot.stop();
         
         robot.right();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.02)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
        sleep (1000);
        
        // from position 2 to 3
        robot.left();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.65)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
        robot.right();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.02)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
    
          sleep (1000);
          
          
           robot.left();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 1.2)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop(); 
        
          robot.right();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.02)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();  
        
        
        
          robot.spinLeft();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 1)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();  
        
          robot.spinRight();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.02)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();  
        
        
        //After sampling
          robot.backward();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 1.6)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        
        robot.forward();
        robot.stop();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.02)){
            //telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();  
         
        robot.marker.setPosition(0.75);
        
         sleep (2000);
        
          robot.forward();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <=2)){
            telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
        
        robot.marker.setPosition(0);


        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        boolean goldColor = false;
        if(hsvValues[0] < 40){
            goldColor = true;
            telemetry.addData("Bannana color", goldColor);
            telemetry.addData("Hue", hsvValues[0]);
        }else if (hsvValues[0] > 120){
            goldColor = false;
            telemetry.addData("Not Bannana Color", goldColor);
            telemetry.addData("Hue", hsvValues[0]);
        } else {
            goldColor = false;
            telemetry.addData("Neither", goldColor);
            telemetry.addData("Hue", hsvValues[0]);
        }
        telemetry.update();
            
            
            sleep(10000);
            
        // if (boolean goldColor = true); {
        //     while (opModeIsActive(); && runtime.seconds()  <  1); {
        //     robot.forward();
         
            
        // }
            
//         // Drive towards to sample
//         robot.forward();
//         runtime.reset();
//         while(opModeIsActive() && (runtime.seconds() <= 0.8)) {
//             telemetry.addData("Arriving", "Time: %2.5f S Elapsed", runtime.seconds());
//         }
//         robot.stop();
// //UNCOMMENT FOR SAMPLING
// //        robot.sample.setPosition(0.5);

// //// Add color logic here at a later time

//         ///////////////////////////////////////////////////////////////////////////////////////////////////
//         //////////////////////////////////// Move Towards Depot ///////////////////////////////////////////
//         ///////////////////////////////////////////////////////////////////////////////////////////////////

//         robot.left();
//         runtime.reset();
//         while (opModeIsActive ()  &&  (runtime.seconds() <= 1.3))    {
//             telemetry.addData ("Positioning1", "Time: %2.5f S Elapsed", runtime.seconds());
//             telemetry.update ();
//         }
//         robot.stop();

//         robot.spinLeft();
//         while (opModeIsActive ()  &&  (runtime.seconds() <= 0.75)) {
//             telemetry.addData("Turning", "Time: %2.5f S Elapsed", runtime.seconds());
//             telemetry.update();
//         }
//         robot.stop();

//         robot.forward();
//         while (opModeIsActive ()   &&  (runtime.seconds()   <= 4)) {
//             telemetry.addData("Positioning1", "Time: %2.5f S Elapsed", runtime.seconds());
//             telemetry.update();
//         }
//         robot.stop();
        
// //UNCOMMENT FOR MARKER PLACEMENT
// //        robot.marker.setPosition(0);

//         robot.backRight();
//         while (opModeIsActive ()  &&  (runtime.seconds() <= 0.5)) {
//             telemetry.addData("Diagonal to Crater", "Time: %2.5f S Elapsed", runtime.seconds());
//             telemetry.update();
//         }
//         robot.stop();

//         robot.backward();
//         while (opModeIsActive ()  &&  (runtime.seconds() <= 5)) {
//             telemetry.addData("Back From Crater", "Time: %2.5f S Elapsed", runtime.seconds());
//             telemetry.update();
//         }
        robot.stop();
                     // send the info back to driver station using telemetry function.
            // telemetry.addData("Alpha", robot.sensorColor.alpha());
            // telemetry.addData("Red  ", robot.sensorColor.red());
            // telemetry.addData("Green", robot.sensorColor.green());
            // telemetry.addData("Blue ", robot.sensorColor.blue());
            
            

    }
}
