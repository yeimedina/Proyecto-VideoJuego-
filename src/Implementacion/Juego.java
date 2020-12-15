package Implementacion;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


import Clases.Fondo;
import Clases.Item;
import Clases.JugadorAnimado;
import Clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Juego extends Application{
	Group root;
	Scene escena;
	Canvas lienzo;
	GraphicsContext graficos;
	private int x;
	//private Jugador jugador;
	private JugadorAnimado jugadorAnimado;
	private JugadorAnimado jugadorAnimado2;
	private Fondo fondo;
	//private Tile tile;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean izquierda;
	public static boolean derecha;
	public static HashMap<String, Image>imagenes;
	public ArrayList<Tile>tile;
	private Item item;
	private Item item1;
	private Item item2;
	private Item item3;
	private Item item4;
	
	
	private int  tilemap[][] =  {
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0},
	};
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
	
	@Override
	public void start(Stage ventana) throws Exception {
//		String path = "C:/Users/Dell/Documents/Software/Eclipse Projects/Videojuego/src/Musica/allo.mp3";
//		Media media = new Media(new File(path).toURI()                                                                                                   .toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.setAutoPlay(true);
//		//MediaView mediaView = new MediaView(mediaPlayer);
		
		
		inicializarComponentes();
		gestionEventos();
		pintar();
		ventana.setScene(escena);
		// set icon 
		ventana.getIcons().add(new Image("Imagenes/icon.png"));
		ventana.setTitle("Astronaut Work");
		ventana.show();
		cicloJuego();
	}//end start
	
	
	
	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer AnimationTimer = new AnimationTimer() {
			//este metodo se ejecuta 60 veces por segundo 60FPS
			@Override
			public void handle(long tiempoActual) {
			double t = (tiempoActual - tiempoInicial) / 1000000000.0;
			System.out.println(t);
			actualizarEstado(t);
			pintar();
			}
		};
		AnimationTimer.start();//Empieza el ciclo del juego
	}//end CicloJuego
	
	
	
	public void actualizarEstado(double t) {
		//jugador.mover();
		jugadorAnimado.verificarColicionesItem(item);
		jugadorAnimado.verificarColicionesItem(item1);
		jugadorAnimado.verificarColicionesItem(item2);
		jugadorAnimado.verificarColicionesItem(item3);
		jugadorAnimado.verificarColicionesItem(item4);
		jugadorAnimado.calcularFrame(t);
		jugadorAnimado.mover();
		jugadorAnimado2.calcularFrame(t);
		jugadorAnimado2.mover();
		fondo.mover();
		for(int i=0; i<tile.size();i++) {
			tile.get(i).mover();
		}
	}//end actualizar
	
	
	
	public void inicializarComponentes() {
		imagenes = new HashMap<String, Image>();
		cargarImagenes();
		//jugador = new Jugador(20, 40, "astronauta", 3, 0);
		jugadorAnimado = new JugadorAnimado(100,200, "megaman", 3, 0, "descanso");
		jugadorAnimado2 = new JugadorAnimado(300,200, "2", 3, 0, "descanso");
		
		//jugadorAnimado = new JugadorAnimado(300,200, "astronauta2", 3, 0, "descanso");
		//jugadorAnimado = new JugadorAnimado(100,200, "megaman", 3, 0, "descanso");
		
		fondo = new Fondo(0,0,"escena11","escena12",5);
		inicializarTiles();
		item = new Item(300, 300, "item",0,1);
		item1 = new Item(600, 300, "item",0,1);
		item2 = new Item(300, 600, "item",0,1);
		item3 = new Item(500, 200, "item",0,1);
		item4 = new Item(700, 300, "item",0,1);
		//tile = new Tile(0, 0, "tilemap",0, 259,257, 63, 63);
		root = new Group();
		escena = new Scene(root, 1280, 800);
		lienzo = new Canvas(1280, 800);
		root.getChildren().add(lienzo);
		graficos = lienzo.getGraphicsContext2D();
	}//end Inic.
	
	
	
	public void inicializarTiles(){
		tile = new ArrayList<Tile>();
		for(int i=0; i<tilemap.length; i++) {
			for(int j=0; j<tilemap[i].length;j++) {
				if(tilemap[i][j]!=0) {
					this.tile.add(new Tile(tilemap[i][j],j*33, i*33, "escena15", 0, 70, 35));
				}
			}
		}
	}
	
	public void cargarImagenes() {
		imagenes.put("astronauta", new Image("Imagenes/astronaut2.png"));
		imagenes.put("astronautaFurioso", new Image("Imagenes/h.png"));
		imagenes.put("escenario", new Image("Imagenes/escenario1.jpg"));
		imagenes.put("escenariodos", new Image("Imagenes/escenario2.jpg"));
		imagenes.put("tilemap", new Image("Imagenes/titlemap.png"));
		imagenes.put("fondouno", new Image("Imagenes/fondospace.jpg"));
		imagenes.put("escena12", new Image("Imagenes/escena12.jpg"));
		imagenes.put("escena11", new Image("Imagenes/escena11.jpg"));
		imagenes.put("escena15", new Image("Imagenes/escena15.png"));
		imagenes.put("megaman", new Image("Imagenes/sprites.png"));
		imagenes.put("2", new Image("Imagenes/sprites2.png"));
		imagenes.put("astronauta2", new Image("Imagenes/sprites2.png"));
		imagenes.put("item", new Image("Imagenes/item.png"));
		
	}
	
	
	public void pintar() {
		//graficos.fillRect(0,0,100,100);
		fondo.pintar(graficos);
		/*
		 cooordenadaXDentroimagen,coordenadaYDentroimagen,ancho,alto,coordenadaXPintar,coordenadaYPintar,anchoPintar,altoPintar 
		*/
		//graficos.drawImage(imagenes.get("tilemap"), 259,257,63,63,0,0,63,63);
		//tile.pintar(graficos);
		for(int i=0; i<tile.size(); i++)
			tile.get(i).pintar(graficos);
		//pintar al jugador
		jugadorAnimado.pintar(graficos);
		jugadorAnimado2.pintar(graficos);
		item.pintar(graficos);
		item1.pintar(graficos);
		item2.pintar(graficos);
		item3.pintar(graficos);
		item4.pintar(graficos);
		graficos.fillText( "Cantidad de vidas: "+ jugadorAnimado.getVidas(), 10,757);
		//graficos.setFill(Color.WHITE);
	}//end pintar
	
	
	public void gestionEventos() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>(){
			//el metodo handler se ejecuta cada vez que se presiona una tecla 
			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()){
				case "RIGHT":
					derecha = true;
					jugadorAnimado.setDireccion(1);
					jugadorAnimado.setAnimacionActual("correr");
					break;
				case "LEFT":
					izquierda = true;
					jugadorAnimado.setDireccion(-1);
					jugadorAnimado.setAnimacionActual("correr");
					break;
				case "UP":
					arriba=true;
					jugadorAnimado.setAnimacionActual("correr");
					break;
				case "DOWN": 
					abajo = true;
					jugadorAnimado.setAnimacionActual("correr");
					break;
				case "SPACE": 
					jugadorAnimado.setVelocidad(10);
					//jugador.setNombreImagen("Imagenes/h.png");
					break;
				}
			}
		});
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()){
				case "RIGHT":
					derecha = false;
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "LEFT":
					izquierda = false;
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "UP":
					arriba = false;
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "DOWN": 
					abajo = false;
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "SPACE": 
					jugadorAnimado.setVelocidad(3);
					//jugador.setNombreImagen("Imagenes/astronaut2.png");
					break;
				}
			}
		});
	}//end gestionEvento
}//end
