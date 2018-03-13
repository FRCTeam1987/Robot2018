package org.usfirst.frc.team1987.robot;

import edu.wpi.first.wpilibj.command.Command;
import openrio.powerup.MatchData;

public class AutonomousContainer {

	private final Command command;
	private final AutonomousMode mode;
	private final String name;
	private final MatchData.OwnedSide scalePosition;
	private final StartingPosition startingPosition;
	private final MatchData.OwnedSide switchPosition;
	
	public AutonomousContainer(final Builder builder) {
		this.command = builder.command;
		this.mode = builder.mode;
		this.name = builder.name;
		this.scalePosition = builder.scalePosition;
		this.startingPosition = builder.startingPosition;
		this.switchPosition = builder.switchPosition;
	}
	
	public Command getCommand() {
		return this.command;
	}
	
	public AutonomousMode getMode() {
		return this.mode;
	}
	
	public String getName() {
		return this.name;
	}
	
	public MatchData.OwnedSide getScalePosition() {
		return this.scalePosition;
	}
	
	public StartingPosition getStartingPosition() {
		return this.startingPosition;
	}
	
	public MatchData.OwnedSide getSwitchPosition() {
		return this.switchPosition;
	}
	
	public static class Builder {
		
		private Command command;
		private AutonomousMode mode;
		private final String name;
		private MatchData.OwnedSide scalePosition;
		private StartingPosition startingPosition;
		private MatchData.OwnedSide switchPosition;
		
		public Builder(final String name) {
			this.name = name;
		}
		
		public Builder withCommand(final Command command) {
			this.command = command;
			return this;
		}
		
		public Builder withMode(final AutonomousMode mode) {
			this.mode = mode;
			return this;
		}
		
		public Builder withScalePosition(final MatchData.OwnedSide scalePosition) {
			this.scalePosition = scalePosition;
			return this;
		}
		
		public Builder withStartingPosition(final StartingPosition startingPosition) {
			this.startingPosition = startingPosition;
			return this;
		}
		
		public Builder withSwitchPosition(final MatchData.OwnedSide switchPosition) {
			this.switchPosition = switchPosition;
			return this;
		}
		
		public AutonomousContainer build() {
			return new AutonomousContainer(this);
		}
	}
	
}
