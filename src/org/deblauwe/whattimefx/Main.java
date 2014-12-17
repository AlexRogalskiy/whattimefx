package org.deblauwe.whattimefx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main extends Application
{

	private Label m_timeLabel;
	private Label m_hexColorLabel;
	private GridPane m_gridPane;

	@Override
	public void start( Stage primaryStage ) throws Exception
	{
		primaryStage.setTitle( "What Colour Is It JavaFx?" );

		m_timeLabel = new Label();
		m_timeLabel.getStyleClass().add( "time-label" );

		m_hexColorLabel = new Label();
		m_hexColorLabel.getStyleClass().add( "hexcolor-label" );
		m_hexColorLabel.setMaxWidth( Double.MAX_VALUE );

		m_gridPane = new GridPane();
		m_gridPane.setAlignment( Pos.CENTER );
		m_gridPane.setVgap( 20 );

		m_gridPane.add( m_timeLabel, 0, 0 );
		m_gridPane.add( m_hexColorLabel, 0, 1 );

		Scene value = new Scene( m_gridPane, 300, 275 );
		value.getStylesheets().add( "org/deblauwe/whattimefx/style.css" );
		primaryStage.setScene( value );

		bindToTime();
		primaryStage.show();
	}


	public static void main( String[] args )
	{
		launch( args );
	}

	private void bindToTime()
	{
		Timeline timeline = new Timeline(
				new KeyFrame( Duration.seconds( 0 ),
							  new EventHandler<ActionEvent>()
							  {
								  @Override
								  public void handle( ActionEvent actionEvent )
								  {
									  Calendar time = Calendar.getInstance();
									  SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm:ss" );
									  setText( simpleDateFormat.format( time.getTime() ) );
								  }
							  }
				),
				new KeyFrame( Duration.seconds( 1 ) )
		);
		timeline.setCycleCount( Animation.INDEFINITE );
		timeline.play();
	}

	private void setText( String format )
	{
		m_timeLabel.setText( format );
		String color = "#" + format.replaceAll( ":", "" );
		m_hexColorLabel.setText( color );
		m_gridPane.setBackground( new Background( new BackgroundFill( Color.web( color ), null, null ) ) );

	}

}
