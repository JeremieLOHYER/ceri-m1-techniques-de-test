package fr.univavignon.pokedex.api;

import org.junit.*;
import org.junit.function.ThrowingRunnable;
import org.mockito.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
public class IPokedexTest {
    @Mock
    private IPokedex pokedex;

    private Random random;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        random = new Random();
        random.setSeed(LocalTime.now().toNanoOfDay());
    }

    @Test
    public void testSize() {

        int expectedSize = random.nextInt() % 300;

        Mockito.when(pokedex.size()).thenReturn(expectedSize);

        int actualSize = pokedex.size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testAddPokemon() {

        int expectedOldSize = random.nextInt() % 300;

        int expectedUniqueIndex = expectedOldSize;

        Pokemon aPokemon = MyPokemons.BULBIZARRE;

        Mockito.when(pokedex.addPokemon(aPokemon)).thenReturn(expectedUniqueIndex);

        int actualUniqueIndex = pokedex.addPokemon(aPokemon);

        assertEquals(expectedUniqueIndex, actualUniqueIndex);
    }

    @Test
    public void testGetPokemonThrowingWhenLessThan0() {

        int index = 0 - random.nextInt();

        try {
            Mockito.when(pokedex.getPokemon(index))
                    .thenThrow(PokedexException.class);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        // Vérification du résultat
        assertThrows(PokedexException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                pokedex.getPokemon(index);
            }
        });
    }

    @Test
    public void testGetPokemonThrowingWhenMoreThanSize() {

        int size = random.nextInt() % 300;

        int index = size + random.nextInt();

        try {
            Mockito.when(pokedex.getPokemon(index))
                    .thenThrow(PokedexException.class);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        // Vérification du résultat
        assertThrows(PokedexException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                pokedex.getPokemon(index);
            }
        });
    }

    @Test
    public void testGetPokemon() {

        int size = random.nextInt() % 300;

        int index = random.nextInt() % size;

        Pokemon expectedPokemon = MyPokemons.BULBIZARRE;

        Pokemon actualPokemon = null;

        try {
            Mockito.when(pokedex.getPokemon(index)).thenReturn(expectedPokemon);

            actualPokemon = pokedex.getPokemon(index);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expectedPokemon, actualPokemon);
    }

    @Test
    public void testGetPokemons() {

        Pokemon aPokemon = MyPokemons.BULBIZARRE;
        Pokemon anotherPokemon = MyPokemons.AQUALI;
        Pokemon againAPokemon = MyPokemons.BULBIZARRE;

        List<Pokemon> expectedPokemons = new ArrayList<>();
        expectedPokemons.add(aPokemon);
        expectedPokemons.add(anotherPokemon);
        expectedPokemons.add(againAPokemon);

        Mockito.when(pokedex.getPokemons()).thenReturn(expectedPokemons);

        List<Pokemon> actualPokemons = pokedex.getPokemons();

        assertEquals(expectedPokemons, actualPokemons);
    }

    @Test
    public void testGetPokemonsCompared() {

        Pokemon aPokemon = MyPokemons.BULBIZARRE;
        Pokemon anotherPokemon = MyPokemons.AQUALI;
        Pokemon againAPokemon = MyPokemons.BULBIZARRE;

        List<Pokemon> expectedPokemons = new ArrayList<>();
        expectedPokemons.add(aPokemon);
        expectedPokemons.add(anotherPokemon);
        expectedPokemons.add(againAPokemon);

        Comparator<Pokemon> usedComparator = new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return Integer.compare(o1.getHp(),o2.getHp());
            }
        };

        Mockito.when(pokedex.getPokemons(usedComparator)).thenReturn(expectedPokemons);

        List<Pokemon> actualPokemons = pokedex.getPokemons(usedComparator);

        assertEquals(expectedPokemons, actualPokemons);
    }
}
