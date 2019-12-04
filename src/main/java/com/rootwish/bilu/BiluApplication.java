package com.rootwish.bilu;

import com.rootwish.bilu.view.IndexView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rootwish.bilu.mapper")
public class BiluApplication extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) {
//		SpringApplication.run(BiluApplication.class, args);
		launch(BiluApplication.class , IndexView.class ,args);
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception{
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(BiluApplication.class.getResource("IndexView.fxml"));
//
//		HBox rootLayout = (HBox) loader.load();
//
//		primaryStage.setScene(new Scene(rootLayout, 225, 275));
//		primaryStage.show();
//
//	}

}
