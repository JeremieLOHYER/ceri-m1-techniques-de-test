package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.*;

import java.time.LocalTime;
import java.util.Random;

import static org.junit.Assert.*;

public class IPokemonTrainerFactoryTest {
    @Mock
    private IPokemonTrainerFactory pokemonTrainerFactory;

    @Mock
    private IPokedexFactory pokedexFactory;

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
    public void testCreateTrainer() {

        String name = "jesuisco";

        Team team = null;

        switch (random.nextInt() % 3) {
            case 0:
                team = Team.VALOR;
                break;
            case 1:
                team = Team.INSTINCT;
                break;
            case 2:
                team = Team.MYSTIC;
                break;
        }

        PokemonTrainer expectedPokemonTrainer = new PokemonTrainer(name, team, pokedex);

        Mockito.when(pokemonTrainerFactory.createTrainer(name, team, pokedexFactory)).thenReturn(expectedPokemonTrainer);

        PokemonTrainer actualPokemonTrainer = pokemonTrainerFactory.createTrainer(name,team,pokedexFactory);

        assertEquals(expectedPokemonTrainer, actualPokemonTrainer);

        assertEquals(expectedPokemonTrainer.getName(), actualPokemonTrainer.getName());
        assertEquals(expectedPokemonTrainer.getPokedex(), actualPokemonTrainer.getPokedex());
        assertEquals(expectedPokemonTrainer.getTeam(), actualPokemonTrainer.getTeam());
    }
}
