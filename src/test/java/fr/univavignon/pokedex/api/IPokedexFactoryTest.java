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
    @Mock
    private IPokedexFactory pokedexFactory;

    @Mock
    private IPokedex pokedex;

    @Mock
    private IPokemonMetadataProvider pokemonMetadataProvider;

    @Mock
    private IPokemonFactory pokemonFactory;

    private Random random;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        random = new Random();
        random.setSeed(LocalTime.now().toNanoOfDay());
    }

    @Test
    public void testCreatePokedex() {

        IPokedex expectedPokedex = pokedex;

        Mockito.when(pokedexFactory.createPokedex(pokemonMetadataProvider,pokemonFactory)).thenReturn(expectedPokedex);

        IPokedex actualPokedex = pokedexFactory.createPokedex(pokemonMetadataProvider,pokemonFactory);

        assertEquals(expectedPokedex, actualPokedex);
    }
}
