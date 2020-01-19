package gameClient;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import de.micromata.opengis.kml.v_2_2_0.*;
import element.Fruits;
import element.Robots;
import utils.StdDraw;

public class KML_Logger {

    public void objKML() throws ParseException, InterruptedException {
        Kml kml = new Kml();
        Document d = kml.createAndSetDocument();
        int i = 0;
        if (StdDraw.gameGui!=null && StdDraw.gameGui.getServer()!=null){
            while (StdDraw.gameGui.getServer().isRunning()){
                Thread.sleep(100);
                i++;
                List<Robots> r = StdDraw.gameGui.getRobots().robots;
                List<Fruits> f =StdDraw.gameGui.getFruits().fruits;
                for(Robots rb : r){
                    Placemark p = d.createAndAddPlacemark();
                    Icon ic = new Icon();
                    ic.setHref("http://pngimg.com/uploads/monkey/monkey_PNG18735.png");
                    ic.setViewBoundScale(1);
                    ic.setViewRefreshTime(1);
                    ic.withRefreshInterval(1);
                    IconStyle is = new IconStyle();
                    is.setScale(1);
                    is.setHeading(1);
                    is.setColor("ff007db3");
                    is.setIcon(ic);
                    p.createAndAddStyle().setIconStyle(is);
                    p.withDescription("Mac: "+"\nType: Monkey").withOpen(Boolean.TRUE).createAndSetPoint().addToCoordinates(rb.getLocation().x(), rb.getLocation().y());
                    String t1 = millisToString(stringToMillis(timeNoe())+i*1000);
                    String t2 = millisToString(stringToMillis(timeNoe())+(i+1)*1000);
                    String[] s1 = t1.split(" ");
                    t1 = s1[0] + "T" + s1[1] + "Z";
                    String[] s2 = t2.split(" ");
                    t2 = s2[0] + "T" + s2[1] + "Z";
                    TimeSpan t = p.createAndSetTimeSpan();
                    t.setBegin(t1);
                    t.setEnd(t2);
                }
                for(Fruits rb : f){
                    Placemark p = d.createAndAddPlacemark();
                    Icon ic = new Icon();
                    ic.setHref("http://pngimg.com/uploads/banana/banana_PNG842.png");
                    ic.setViewBoundScale(1);
                    ic.setViewRefreshTime(1);
                    ic.withRefreshInterval(1);
                    IconStyle is = new IconStyle();
                    is.setScale(1);
                    is.setHeading(1);
                    is.setColor("ff007db3");
                    is.setIcon(ic);
                    p.createAndAddStyle().setIconStyle(is);
                    p.withDescription("Mac: "+"\nType: Banana").withOpen(Boolean.TRUE).createAndSetPoint().addToCoordinates(rb.getLocation().x(), rb.getLocation().y());
                    String t1 = millisToString(stringToMillis(timeNoe())+i*1000);
                    String t2 = millisToString(stringToMillis(timeNoe())+(i+1)*1000);
                    String[] s1 = t1.split(" ");
                    t1 = s1[0] + "T" + s1[1] + "Z";
                    String[] s2 = t2.split(" ");
                    t2 = s2[0] + "T" + s2[1] + "Z";
                    TimeSpan t = p.createAndSetTimeSpan();
                    t.setBegin(t1);
                    t.setEnd(t2);
                }
            }
        }
        try{
            kml.marshal(new File("KmlRun.kml"));
            System.out.println("Create");
        }
        catch (Exception e){
            System.out.println("Fail create");
        }
    }

    private String millisToString(Long m){
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return s.format(new Date((m)));
    }

    private Long stringToMillis(String m)throws ParseException{
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
        Date d = s.parse(m.toString());
        long l = d.getTime();
        return l;
    }

    private String timeNoe(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}
