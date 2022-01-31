package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    Constants constants = new Constants();

    final int cpr = 2048; //counts per rev

    TalonFX bottomMotor, topMotor;
    
    public Shooter() {
        
        bottomMotor = new TalonFX(9);

        //PID
        bottomMotor.config_kP(0, 0.1);
        bottomMotor.config_kI(0, 0);
        bottomMotor.config_kD(0, 0);

        topMotor = new TalonFX(10);
        topMotor.config_kP(0, 0.1);
        topMotor.config_kI(0, 0);
        topMotor.config_kD(0, 0);
    }

    public void update(double bottomOutputRPM, double topOutputRPM) {
        
        double vTop = (topOutputRPM / 60000) * cpr;
        double vBottom = (bottomOutputRPM / 60000) * cpr;

        //Velocity tracking should be closed loop, in counts/100ms
        if(topMotor.getSupplyCurrent() < 50 && bottomMotor.getSupplyCurrent() < 50 && vTop != 0) {
            bottomMotor.set(ControlMode.Velocity, vBottom);
            topMotor.set(ControlMode.Velocity, vTop);
        }
        else {
            bottomMotor.set(ControlMode.PercentOutput, 0);
            topMotor.set(ControlMode.PercentOutput, 0);
        }

        System.out.println("Top Vel: " + topMotor.getClosedLoopTarget() + topMotor.getClosedLoopError());
        System.out.println("Bottom Vel: " + bottomMotor.getClosedLoopTarget() + bottomMotor.getClosedLoopError());

        System.out.println("Top Percent Output: " + topMotor.getMotorOutputPercent());
        System.out.println("Bottom Percent Output: " + bottomMotor.getMotorOutputPercent());

        System.out.println("Top Voltage Draw: " + topMotor.getMotorOutputVoltage());
        System.out.println("Bottom Voltage Draw: " + bottomMotor.getMotorOutputVoltage()); 

    }
}
