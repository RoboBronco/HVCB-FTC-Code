
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Basic Crater", group="Auto")
public class Crater_Basic extends LinearOpMode {

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
        robot.lift.setPower(1);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 3.1)){
            telemetry.addData("Unfolding:", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.lift.setPower(0);

        sleep(500);
        
        //WORKS
        // Detach from lander
        robot.right();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.35)){
            telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }robot.stop();
        
             robot.lift.setPower(1);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.25)){ //1 
            telemetry.addData("Unfolding:", "Time: %2.5f S Elapsed", runtime.seconds());
        }

        robot.backward();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.35)){
            telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
        robot.forward();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.4)){
            telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
        sleep (1000);
        
        robot.right();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.35)){
            telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
        robot.lift.setPower(-1);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 4.5)){ 
            telemetry.addData("Unfolding:", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();
        
        sleep (2000);
        
        
        //Samplling Reposition
              robot.left();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.5)){
            telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// SAMPLING //////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////

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

    }
}
