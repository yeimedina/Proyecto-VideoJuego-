package Clases;
import java.util.HashMap;
import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class JugadorAnimado extends ObjetoJuego {

	private int vidas;
	private HashMap<String, Animacion>animaciones;
	private int xImagen;
	private int yImagen;
	private int altoImagen;
	private int anchoImagen;
	private String animacionActual;
	private int direccion;
	
	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public String getAnimacionActual() {
		return animacionActual;
	}

	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}

	public JugadorAnimado(int x, int y, String nombreImagen, int velocidad, int vidas, String animacionActual) {
		super(x, y, nombreImagen, velocidad);
		this.vidas = vidas;
		this.animacionActual = animacionActual;
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
	}
	
	public void inicializarAnimaciones() {
		Rectangle coordenadasCorrer[]= {
			new Rectangle(15,12,77,97),
			new Rectangle(94,1,77,99),//161
			new Rectangle(173,10,77,99),//250
			new Rectangle(270,9,77,100),//331
			new Rectangle(348,4,77,99),//12
			new Rectangle(428,9,77,98)//501
			
		};
		Animacion animacionCorrer = new Animacion(0.09, coordenadasCorrer);
		animaciones.put("correr", animacionCorrer);
		
		Rectangle coordenadasDescanso[]= {
				new Rectangle(20,163,77,97),
		};
		Animacion animacionDescanso = new Animacion(0.09, coordenadasDescanso);
		animaciones.put("descanso", animacionDescanso);
		
	}
	
	public void calcularFrame(double t) {
		Rectangle coordenadas = animaciones.get(animacionActual).calcularFramaActual(t);
		this.xImagen  = (int)coordenadas.getX();
		this.yImagen = (int)coordenadas.getY();
		this.altoImagen = (int)coordenadas.getHeight();
		this.anchoImagen = (int)coordenadas.getWidth();
		
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(x, y, (direccion*anchoImagen)-10, altoImagen);
	}
	//se ejecuta por cada iteraccion del ciclo del juego
	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, yImagen, anchoImagen, altoImagen, x + (direccion==-1?anchoImagen:0), y, direccion*anchoImagen, altoImagen);
		//graficos.setStroke(Color.RED);
		//graficos.strokeRect(x, y, anchoImagen-22, altoImagen);
		
	}
	//se ejecuta por cada iteraccion del ciclo del juego
	@Override
	public void mover() {
		if(x>700)
			x=-80;
		if(Juego.derecha) 
			x += velocidad;
		if(Juego.izquierda) 
			x -= velocidad;
		if(Juego.arriba) 
			y -= velocidad;
		if(Juego.abajo) 
			y += velocidad;
	}
	
	
	public void verificarColicionesItem(Item item) {
		if(!item.isCapturado() && this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
			this.vidas += item.getCantidadVidas();
			item.setCapturado(true);
		}
			
	}
}
