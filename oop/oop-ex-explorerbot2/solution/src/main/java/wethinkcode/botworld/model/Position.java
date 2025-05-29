package wethinkcode.botworld.model;

import java.util.Objects;

public class Position
{
    private final int x;
    private final int y;

    public Position( int xValue, int yValue ){
        x = xValue;
        y = yValue;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public Coord incrementX(){
        return new Position( x() + 1, y() );
    }

    public Coord decrementX(){
        return new Position( x() - 1, y() );
    }

    public Coord incrementY(){
        return new Position( x(), y() + 1 );
    }

    public Coord decrementY(){
        return new Position( x(), y() - 1 );
    }

    @Override
    public boolean equals( Object obj ){
        if( ! (obj instanceof Coord other) ) return false;
        return x() == other.x() && y() == other.y();
    }

    @Override
    public int hashCode(){
        return Objects.hash( x(), y() );
    }

    @Override
    public String toString(){
        return "Position{" + x() + '@' + y() + '}';
    }
}
