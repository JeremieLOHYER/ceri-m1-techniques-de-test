package fr.univavignon.pokedex.api;

import org.junit.*;
import org.junit.function.ThrowingRunnable;
import org.mockito.*;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Random;

import static org.junit.Assert.*;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;
    private Random random;

    @Before
    public void setUp() {

        metadataProvider = new PokemonMetadataProvider();

        random = new Random();
        random.setSeed(LocalTime.now().toNanoOfDay());
    }

    @Test
    public void testThrowingGetPokemonMetadataLessThan0() {

        int index = -1;

        // Vérification du résultat
        try {
            metadataProvider.getPokemonMetadata(index);
        } catch (PokedexException e) {
            Assert.assertEquals(e.getMessage(),"get pokemon index less than 0");
        }

    }

    @Test
    public void testThrowingGetUnknownPokemonMetadata() {

        int index = 151;

        // Vérification du résultat
        try {
            metadataProvider.getPokemonMetadata(index);
        } catch (PokedexException e) {
            Assert.assertEquals(e.getMessage(),"get unknown pokemon metadata");
        }

    }
    @Test
    public void testGetPokemonMetadata() {

        // Définition du comportement du mock pour l'index donné
        int index = 1;
        PokemonMetadata expectedMetadata = MyPokemons.AQUALI;

        PokemonMetadata actualMetadata = null;
        try {
            // Appel de la méthode à tester
            actualMetadata = metadataProvider.getPokemonMetadata(MyPokemons.AQUALI.getIndex());
        } catch (PokedexException e) {
            System.out.println("oh no, cringe");
        }

        // Vérification du résultat
        assertNotNull(actualMetadata);
        assertEquals(expectedMetadata.getName(), actualMetadata.getName());
        assertEquals(expectedMetadata.getIndex(), actualMetadata.getIndex());
        assertEquals(expectedMetadata.getAttack(), actualMetadata.getAttack());
        assertEquals(expectedMetadata.getDefense(), actualMetadata.getDefense());
        assertEquals(expectedMetadata.getStamina(), actualMetadata.getStamina());
    }
}
