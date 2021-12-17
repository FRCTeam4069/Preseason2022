package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    
    Constants constants = new Constants();

    CANSparkMax dropDown;
    CANSparkMax intake;

    double errorSum = 0;

    public Intake() {

        dropDown = new CANSparkMax(constants.IT_DROP_DOWN_ID, MotorType.kBrushless);
        intake = new CANSparkMax(constants.IT_DRIVE_ID, MotorType.kBrushless);

        dropDown.getEncoder().setPosition(0);
    }

    public void drop() {
        double output;
        double setpoint = 0;
        double lastError = 0;

        double lastTime = 0;

        double currentTime = System.currentTimeMillis();
        double error = setpoint - dropDown.getEncoder().getPosition();

        errorSum += error;

        double changeError = error - lastError;

        double changeTime = (currentTime - lastTime);

        double derr = changeError / changeTime;

        double kP = 0;
        double kI = 0;
        double kD = 0;

        output = error * kP + errorSum * kI + derr * kD;

        dropDown.set(output);

        lastError = error;
        lastTime = currentTime;
    }
}
