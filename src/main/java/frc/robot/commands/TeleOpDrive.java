package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TeleOpDrive extends CommandBase {
    
    private Drivetrain drivetrain;
    private GenericHID controller;

    public TeleOpDrive(Drivetrain drivetrain, GenericHID controller) {
        this.drivetrain = drivetrain;
        this.controller = controller;

        addRequirements(this.drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.basicDrive(0, 0);
    }

    @Override
    public void execute() {
        double velocityPercent = 0;
        double turnPercent = 0;
        if(Math.abs(controller.getY(Hand.kLeft)) > 0.07 || Math.abs(controller.getX(Hand.kRight)) > 0.07) {
            velocityPercent = controller.getY(Hand.kLeft);
            turnPercent = controller.getX(Hand.kRight);
        }

        drivetrain.basicDrive(velocityPercent, turnPercent);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
