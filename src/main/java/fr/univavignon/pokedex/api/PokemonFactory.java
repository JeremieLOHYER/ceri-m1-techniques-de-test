package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory{
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        PokemonMetadata metadata = null;
        double iv = 0.5;
        try {
            metadata = new PokemonMetadataProvider().getPokemonMetadata(index);
        } catch (PokedexException e) {
            System.out.println("createPokemon impossible : "+e.getMessage());
        }
        return new Pokemon(metadata.getIndex(), metadata.getName(), metadata.getAttack(), metadata.getDefense(), metadata.getStamina(), cp,hp,dust,candy,iv);
    }
}
