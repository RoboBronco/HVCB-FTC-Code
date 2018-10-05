/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Mecanum: Teaching Tele", group="Tele")
public class TeleOp_Mec extends LinearOpMode {

    /* Declare OpMode members. */
   RoverHardware robot           = new RoverHardware();

    @Override
    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("State:", "Ready to Rumble!");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //////////////////////////// DRIVER 1 ------------------ DRIVER 1 //////////////////////

            if(gamepad1.dpad_up) { // Forwards
                robot.forward();
            }else if(gamepad1.dpad_down){ // Backwards
                robot.backward();
            }else if(gamepad1.dpad_left){ // Strafe Left
                robot.left();
            }else if(gamepad1.dpad_right){ // Strafe Right
                robot.right();
            }else if(gamepad1.dpad_left && gamepad1.dpad_up){ // Diagonal Forward-Left
                robot.forwardLeft();
            }else if(gamepad1.dpad_left && gamepad1.dpad_down){ // Diagonal Back-Left
                robot.backLeft();
            }else if(gamepad1.dpad_right && gamepad1.dpad_up){ //Diagonal Forward-Right
                robot.forwardRight();
            }else if(gamepad1.dpad_right && gamepad1.dpad_down) { //Diagonal Back-Right
                robot.backRight();
            }else if(gamepad1.left_bumper){ //Spin left
                robot.spinLeft();
            }else if(gamepad1.right_bumper){ //Spin Right
                robot.spinRight();
            }else{ // Stop
                robot.stop();
            }

            //////////////////////////// DRIVER 2 ------------------ DRIVER 2 //////////////////////

            // Lift Controls //
            if(gamepad2.dpad_up){ // Lift Up
                robot.lift.setPower(1);
            }else if(gamepad2.dpad_down){ // Lift Down
                robot.lift.setPower(-1);
            }else{ // Lift Stop
                robot.lift.setPower(0);
            }

            // Tilt Controls //
            if(gamepad2.left_bumper){ // Tilt Up (towards sky)
                robot.tilt.setPower(1);
            }else if(gamepad2.right_bumper){ // Tilt Down (towards floor)
                robot.tilt.setPower(-1);
            }else{ // Tilt Stop
                robot.tilt.setPower(0);
            }

            //////////////////////////// DRIVER 1 ------------------ DRIVER 2 //////////////////////

            if(gamepad1.start && gamepad2.start){
                robot.flag.setPosition(1);
            }
        }
    }
}
