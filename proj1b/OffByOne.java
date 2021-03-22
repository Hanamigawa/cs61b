public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y){
        x = java.lang.Character.toLowerCase(x);
        y = java.lang.Character.toLowerCase(y);
        int z = x - y;
        return Math.abs(z) == 1;
    };
}
