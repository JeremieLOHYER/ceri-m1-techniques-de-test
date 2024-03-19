package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.*;

import java.time.LocalTime;
import java.util.Random;

import static org.junit.Assert.*;

public class IPokemonFactoryTest {

    private IPokemonFactory factory;

    private Random random;

    @Before
    public void setUp() {
        factory = new PokemonFactory();

        random = new Random();
        random.setSeed(LocalTime.now().toNanoOfDay());
    }

    @Test
    public void testCreatePokemon() {

        Pokemon expectedPokemon = MyPokemons.AQUALI;

        Pokemon actualPokemon = factory.createPokemon(MyPokemons.AQUALI.getIndex(), MyPokemons.AQUALI.getCp(), MyPokemons.AQUALI.getHp(), MyPokemons.AQUALI.getDust(), MyPokemons.AQUALI.getCandy());

        assertNotNull(actualPokemon.getIv());
        assertEquals(expectedPokemon.getHp(),actualPokemon.getHp());
        assertEquals(expectedPokemon.getCp(),actualPokemon.getCp());
        assertEquals(expectedPokemon.getDust(),actualPokemon.getDust());
        assertEquals(expectedPokemon.getCandy(),actualPokemon.getCandy());
    }
}
