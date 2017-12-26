package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by me on 22/12/2017.
 */

public class earthquakeAdapter extends ArrayAdapter<Earthquake> {

    private Context mContext;

    public earthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
        mContext=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       ViewHolder holder;
        if(convertView==null){
            convertView=LayoutInflater.from(mContext).inflate(R.layout.earthquake_list_item,parent,false);
            holder=new ViewHolder();
            holder.magnitudeView=(TextView) convertView.findViewById(R.id.magnitude);
            holder.locationOffsetView=(TextView) convertView.findViewById(R.id.location_offset);
            holder.locationPrimaryView=(TextView) convertView.findViewById(R.id.location_primary);
            holder.dateView=(TextView) convertView.findViewById(R.id.date);
            holder.timeView=(TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }

        Earthquake currentEarthquake=getItem(position);

        double magnitude=currentEarthquake.getMagnitude();
        holder.magnitudeView.setText(formatMagnitude(magnitude ));
        GradientDrawable magnitudeCircle=(GradientDrawable)holder.magnitudeView.getBackground();
        magnitudeCircle.setColor(getMagnitudeColor(magnitude));

        String[] locationParts=currentEarthquake.getLocation().trim().split(" ");
        holder.locationOffsetView.setText(getOffsetLocation(locationParts));
        holder.locationPrimaryView.setText(getPrimaryLocation(locationParts));

        Long timeInMilliSeconds=currentEarthquake.getTimeInMilliseconds();
        Date date=new Date(timeInMilliSeconds);

        holder.dateView.setText(formateDate(date));
        holder.timeView.setText(formateTime(date));
        return convertView;
    }

    static class ViewHolder {

        private TextView magnitudeView;
        private TextView locationOffsetView;
        private TextView locationPrimaryView;
        private TextView dateView;
        private TextView timeView;
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor=(int)Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId=R.color.magnitude5 ;
                break;
            case 6:
                magnitudeColorResourceId=R.color.magnitude6 ;
                break;
            case 7:
                magnitudeColorResourceId=R.color.magnitude7 ;
                break;
            case 8:
                magnitudeColorResourceId=R.color.magnitude8 ;
                break;
            case 9:
                magnitudeColorResourceId=R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId=R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
    private  String formatMagnitude(double magnitude){
        DecimalFormat formater=new DecimalFormat("0.0");
        return formater.format(magnitude);
    }

    private String getPrimaryLocation(String[] input){
        return input[input.length-2] + " " + input[input.length-1];
    }

    private String getOffsetLocation(String[] input){

        int len=input.length;
        String output="";
        if(len>2){
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<input.length-2;i++){
                sb.append(input[i]);
                sb.append(" ");
            }
            output= sb.toString().trim();
        }
        else{
            output="Near the";
        }
        return output;
    }

    private String formateDate(Date date){
        SimpleDateFormat dateFormater=new SimpleDateFormat("MMMM  DD , yyyy");
        String dateToDisplay=dateFormater.format(date);
        return dateToDisplay;
    }

    private String formateTime(Date date){
        SimpleDateFormat timeFormater=new SimpleDateFormat("h:mm a");
        String timeToDisplay=timeFormater.format(date);
        return timeToDisplay;
    }


}
