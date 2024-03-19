package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.*;

import java.time.LocalTime;
import java.util.Random;

import static org.junit.Assert.*;

public class IPokemonFactoryTest {
    @Mock
    private IPokemonFactory factory;

    private Random random;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        random = new Random();
        random.setSeed(LocalTime.now().toNanoOfDay());
    }

    @Test
    public void testCreatePokemon() {

        int index = random.nextInt() % 150;

        int cp = random.nextInt() % 50;
        int hp = random.nextInt() % 100;
        int dust = random.nextInt() % 50;
        int candy = random.nextInt() % 50;

        Pokemon expectedPokemon = MyPokemons.AQUALI;

        Mockito.when(factory.createPokemon(index, cp,hp,dust,candy)).thenReturn(expectedPokemon);

        Pokemon actualPokemon = factory.createPokemon(index,cp,hp,dust,candy);

        assertEquals(expectedPokemon,actualPokemon);

        assertEquals(expectedPokemon.getIv(),actualPokemon.getIv());
        assertEquals(expectedPokemon.getHp(),actualPokemon.getHp());
        assertEquals(expectedPokemon.getCp(),actualPokemon.getCp());
        assertEquals(expectedPokemon.getDust(),actualPokemon.getDust());
        assertEquals(expectedPokemon.getCandy(),actualPokemon.getCandy());
    }
}
