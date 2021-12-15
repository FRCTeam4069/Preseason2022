package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;

public class TeleOpDrive extends CommandBase {
    
    private Drivetrain drivetrain;
    private XboxController controller;
    private Climber climber;

    public TeleOpDrive(Drivetrain drivetrain, Climber climber, XboxController controller) {
        this.drivetrain = drivetrain;
        this.controller = controller;
        this.climber = climber;

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

        if(controller.getAButton()) drivetrain.shift(Drivetrain.GEAR.HIGH);
        else if(controller.getBButton()) drivetrain.shift(Drivetrain.GEAR.LOW);

        if(controller.getXButton()) climber.update(0.5);
        else if(controller.getYButton()) climber.update(-0.5);

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
