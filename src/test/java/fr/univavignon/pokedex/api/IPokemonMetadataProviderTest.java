package fr.univavignon.pokedex.api;

import org.junit.*;
import org.junit.function.ThrowingRunnable;
import org.mockito.*;

import static org.junit.Assert.*;

public class IPokemonMetadataProviderTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testThrowingGetPokemonMetadata() {

        // Vérification du résultat
        assertThrows(PokedexException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                metadataProvider.getPokemonMetadata(-1);
            }
        });

    }
    @Test
    public void testGetPokemonMetadata() {

        // Définition du comportement du mock pour l'index donné
        int index = 1;
        PokemonMetadata expectedMetadata = new PokemonMetadata(index, "Bulbasaur", 10, 45, 49);

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
