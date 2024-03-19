package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Random;

import static org.junit.Assert.*;

public class IPokedexFactoryTest {
    private IPokemonFactory pokemonFactory;
    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider pokemonMetadataProvider;
    private IPokedex pokedex;

    private Random random;

    @Before
    public void setUp() {
        pokemonFactory = new PokemonFactory();
        pokemonMetadataProvider = new PokemonMetadataProvider();
        pokedex = new Pokedex(pokemonMetadataProvider,pokemonFactory);
        pokedexFactory = new PokedexFactory();

        random = new Random();
        random.setSeed(LocalTime.now().toNanoOfDay());
    }

    @Test
    public void testCreatePokedex() {

        IPokedex actualPokedex = pokedexFactory.createPokedex(pokemonMetadataProvider,pokemonFactory);

        assertNotNull(actualPokedex);
    }
}
