package Control;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.toedter.calendar.DateUtil;
import com.toedter.calendar.IDateEvaluator;

import Modelo.Sesion;



public class ColorearDias implements IDateEvaluator{
	List<Date> listaFecha;
	DateUtil dateUtil = new DateUtil();
	
	public ColorearDias(Vector<Sesion> ses){
	listaFecha=CrearLista(ses);
	Collections.sort(listaFecha);
	if(listaFecha.size() != 0){
		setStarDate(listaFecha.get(0));	 
		setEndDate(listaFecha.get(listaFecha.size()-1));
	} else {
		setStarDate(null);
		setEndDate(null);
	}
	
     
}
	
	
	public List<Date>CrearLista(Vector<Sesion> ses){
		String[] aux;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
		Date dt = new Date();
		List<Date> dias=new ArrayList<>();
		for(int i=0;i<ses.size();i++){
			aux=ses.get(i).getFecha().split("-");
			try {
				dt=formatter.parse(aux[2]+"-"+aux[1]+"-"+aux[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dias.add(dt);
		}
		return dias;
	}
	@Override
	public boolean isSpecial(Date arg0) {
		 return listaFecha.contains(arg0);
	}
	@Override
	public Color getInvalidBackroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getInvalidForegroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInvalidTooltip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getSpecialBackroundColor() {
		return null;
	}

	@Override
	public Color getSpecialForegroundColor() {
		// TODO Auto-generated method stub
		return Color.RED.darker();
	}

	@Override
	public String getSpecialTooltip() {
		// TODO Auto-generated method stub
		return "Click para visualizar la Sesion" ;
	}

	@Override
	public boolean isInvalid(Date arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * Sets the first date in the range to be validated.
     * @param starDate 
     */
    public void setStarDate(Date starDate) {
        dateUtil.setMinSelectableDate(starDate);
    }
	/**
     * @return the initial date in the range to be validated.
     */
    public Date getStartDate() {
        return dateUtil.getMinSelectableDate();
    }

    /**
     * Sets the final date in the range to be validated.
     * @param endDate 
     */
    public void setEndDate(Date endDate) {
        dateUtil.setMaxSelectableDate(endDate);
    }

    /**
     * @return the final date in the range to be validated.
     */
    public Date getEndDate() {
        return dateUtil.getMaxSelectableDate();
	
    }

}