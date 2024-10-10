/*
Starter Code for Blocky Training Robot 2024
Modified by michaudc 2017, 2023
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="MaristBot2024: Blocky Teleop 2024", group="Training")
@Disabled
public class TeleopTank_Blocky_2024 extends OpMode {

    // Create instance of MaristBaseRobot2023
    MaristBaseRobot2024 robot   = new MaristBaseRobot2024(); 

    double clawOffset = 0.0;
    final double CLAW_SPEED = 0.02;
    int armPos = 0;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Robot Ready");    //

        // Set to Run without Encoder for Tele Operated
        robot.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armPos = robot.leftArm.getCurrentPosition();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double armMotorPower = gamepad1.right_trigger = gamepad1.right_trigger - gamepad1.left_trigger;

        if (armMotorPower> 0.1) {
            armPos += gamepad1.right_trigger * 10;
        }

        if (armMotorPower < -0.1) {
            armPos -= gamepad1.left_trigger * 5;
        }

        robot.leftArm.setTargetPosition(armPos);
        robot.leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.leftArm.setPower(0.8);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }


}
