public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y){
        int z = x - y;
        return Math.abs(z) == 1;
    };
}
