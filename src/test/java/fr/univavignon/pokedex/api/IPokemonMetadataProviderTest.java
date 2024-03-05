package fr.univavignon.pokedex.api;

import org.junit.*;
import org.junit.function.ThrowingRunnable;
import org.mockito.*;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Random;

import static org.junit.Assert.*;

public class IPokemonMetadataProviderTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;
    private Random random;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        random = new Random();
        random.setSeed(LocalTime.now().toNanoOfDay());
    }

    @Test
    public void testThrowingGetPokemonMetadataLessThan0() {

        int index = 0 - random.nextInt();

        try {
            Mockito.when(metadataProvider.getPokemonMetadata(index))
                    .thenThrow(PokedexException.class);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        // Vérification du résultat
        assertThrows(PokedexException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                metadataProvider.getPokemonMetadata(index);
            }
        });

    }

    @Test
    public void testThrowingGetPokemonMetadataMoreThan150() {

        int index = 151 + random.nextInt();

        try {
            Mockito.when(metadataProvider.getPokemonMetadata(index))
                    .thenThrow(PokedexException.class);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        // Vérification du résultat
        assertThrows(PokedexException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                metadataProvider.getPokemonMetadata(index);
            }
        });

    }
    @Test
    public void testGetPokemonMetadata() {

        // Définition du comportement du mock pour l'index donné
        int index = 1;
        PokemonMetadata expectedMetadata = new PokemonMetadata(1, "Bulbasaur", 10, 45, 49);

        PokemonMetadata actualMetadata = null;
        try {
            Mockito.when(metadataProvider.getPokemonMetadata(index)).thenReturn(expectedMetadata);

            // Appel de la méthode à tester
            actualMetadata = metadataProvider.getPokemonMetadata(index);
        } catch (PokedexException e) {
            System.out.println("oh no, cringe");
        }

        // Vérification du résultat
        assertEquals(expectedMetadata, actualMetadata);
    }
}
