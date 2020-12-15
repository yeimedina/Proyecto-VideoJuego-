package Clases;

import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Tile extends ObjetoJuego{
	
	private int xImagen;
	private int yImagen;
	private int tipoTile;
	private int velocidad=3;
	
	
	public Tile(int tipoTile, int x, int y, String nombreImagen, int velocidad, int ancho, int alto) {
		super(x, y, nombreImagen, velocidad);
		this.ancho = ancho;
		this.alto = alto;
		
		switch(tipoTile) {
		case 1:
			this.xImagen = 33;
			this.yImagen = 33;
			break;
//		case 2:
//			this.xImagen = 0;
//			this.yImagen = 126;
//			break;
//		case 3:
//			this.xImagen = 0;
//			this.yImagen = 189;
//			break;
//		case 4: 
//			this.xImagen = 0;
//			this.yImagen = 212;
//			break;
		}
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, yImagen, ancho, alto, x, y, alto, ancho);
	}
	@Override
	public void mover() {
		//evento de mover
		if(Juego.derecha) {
			x-=velocidad;
		}
		if(Juego.izquierda) {
			x+=velocidad;
		}	
	}	

}
