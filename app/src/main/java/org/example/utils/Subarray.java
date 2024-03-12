package org.example.utils;

public class Subarray<T> {
    
    private T[] array;
    private long startingPosition;
    private long length;

    public Subarray(T[] _array){
        this(_array, 0L);
    }

    public Subarray(T[] _array, long _startingPosition){
        this(_array, _startingPosition, _array.length - _startingPosition);
    }

    public Subarray(T[] _array, long _startingPosition, long _length){
        this.array = _array;
        this.startingPosition = _startingPosition;
        this.length = _length;
    }

    public Subarray(Subarray<T> _array){
        this(_array.array, _array.startingPosition, _array.length);
    }

    public Subarray(Subarray<T> _subarray, long _startingPosition){
        this(_subarray, _startingPosition, _subarray.length - _startingPosition);
    }

    public Subarray(Subarray<T> _subarray, long _startingPosition, long _length){
        this( _subarray.array, _subarray.startingPosition + _startingPosition,
            _length );
    }

}
