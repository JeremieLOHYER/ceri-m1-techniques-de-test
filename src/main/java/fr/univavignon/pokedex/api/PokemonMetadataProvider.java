package fr.univavignon.pokedex.api;

public class PokemonMetadataProvider implements IPokemonMetadataProvider{
    public static PokemonMetadata BULBIZARRE = new PokemonMetadata(0,"Bulbizarre",126,126,90);
    public static PokemonMetadata AQUALI = new PokemonMetadata(133,"Aquali",186,168,260);


    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if(index < 0) {
            throw new PokedexException("get pokemon index less than 0");
        }
        switch (index) {
            case 0:
                return BULBIZARRE;
            case 133:
                return AQUALI;
            default:
                throw new PokedexException("get unknown pokemon metadata");
        }
    }
}
