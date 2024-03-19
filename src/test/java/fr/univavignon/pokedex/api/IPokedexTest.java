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

    private IPokedex pokedex;

    private Random random;

    @Before
    public void setUp() {
        pokedex = new Pokedex(new PokemonMetadataProvider(), new PokemonFactory());
        pokedex.addPokemon(MyPokemons.BULBIZARRE);

        random = new Random();
        random.setSeed(LocalTime.now().toNanoOfDay());
    }

    @Test
    public void testSize() {

        int expectedSize = 1;

        int actualSize = pokedex.size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testAddPokemon() {

        int expectedOldSize = 1;

        int expectedUniqueIndex = expectedOldSize;

        Pokemon aPokemon = MyPokemons.BULBIZARRE;

        int actualUniqueIndex = pokedex.addPokemon(aPokemon);

        assertEquals(expectedUniqueIndex, actualUniqueIndex);
    }

    @Test
    public void testGetPokemonThrowingWhenLessThan0() {

        int index = -1;
        // Vérification du résultat
        try {
            pokedex.getPokemon(index);
        } catch (PokedexException e) {
            Assert.assertEquals("get pokemon index less than 0", e.getMessage());
        }
    }

    @Test
    public void testGetPokemonThrowingWhenMoreThanSize() {

        int index = 300;
        // Vérification du résultat
        try {
            pokedex.getPokemon(index);
        } catch (PokedexException e) {
            Assert.assertEquals("get pokemon index over size of pokedex",e.getMessage());
        }
    }

    @Test
    public void testGetPokemon() {

        int size = pokedex.size();

        int index = random.nextInt() % size;

        Pokemon expectedPokemon = MyPokemons.BULBIZARRE;

        Pokemon actualPokemon = null;

        try {
            actualPokemon = pokedex.getPokemon(index);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expectedPokemon, actualPokemon);
    }

    @Test
    public void testGetPokemons() {

        Pokemon anotherPokemon = MyPokemons.AQUALI;
        Pokemon againAPokemon = MyPokemons.BULBIZARRE;

        List<Pokemon> expectedPokemons = new ArrayList<>();
        try {
            expectedPokemons.add(pokedex.getPokemon(0));
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        expectedPokemons.add(anotherPokemon);
        expectedPokemons.add(againAPokemon);

        pokedex.addPokemon(anotherPokemon);
        pokedex.addPokemon(againAPokemon);

        List<Pokemon> actualPokemons = pokedex.getPokemons();

        assertEquals(expectedPokemons, actualPokemons);
    }

    @Test
    public void testGetPokemonsCompared() {


        Pokemon anotherPokemon = MyPokemons.AQUALI;
        Pokemon againAPokemon = MyPokemons.BULBIZARRE;

        List<Pokemon> expectedPokemons = new ArrayList<>();
        try {
            expectedPokemons.add(pokedex.getPokemon(0));
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        expectedPokemons.add(anotherPokemon);
        expectedPokemons.add(againAPokemon);

        pokedex.addPokemon(anotherPokemon);
        pokedex.addPokemon(againAPokemon);


        Comparator<Pokemon> usedComparator = new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return Integer.compare(o1.getHp(),o2.getHp());
            }
        };

        expectedPokemons.sort(usedComparator);

        List<Pokemon> actualPokemons = pokedex.getPokemons(usedComparator);

        assertEquals(expectedPokemons, actualPokemons);
    }
}
