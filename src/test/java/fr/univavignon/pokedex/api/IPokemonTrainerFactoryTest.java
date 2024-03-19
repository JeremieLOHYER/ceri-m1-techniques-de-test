package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.*;

import java.time.LocalTime;
import java.util.Random;

import static org.junit.Assert.*;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;

    private IPokedexFactory pokedexFactory;

    private IPokedex pokedex;

    private Random random;

    @Before
    public void setUp() {
        pokemonTrainerFactory = new PokemonTrainerFactory();
        pokedexFactory = new PokedexFactory();
        pokedex = new Pokedex(new PokemonMetadataProvider(), new PokemonFactory());

        random = new Random();
        random.setSeed(LocalTime.now().toNanoOfDay());
    }

    @Test
    public void testTeam() {
        Team valor = Team.VALOR;
        Team instinct = Team.INSTINCT;
        Team mystic = Team.MYSTIC;

        assertEquals(Team.VALOR, valor);
        assertEquals(Team.INSTINCT, instinct);
        assertEquals(Team.MYSTIC, mystic);
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

        PokemonTrainer actualPokemonTrainer = pokemonTrainerFactory.createTrainer(name,team,pokedexFactory);

        assertNotNull(actualPokemonTrainer);

        assertNotNull(actualPokemonTrainer.getPokedex());
        assertEquals(expectedPokemonTrainer.getName(), actualPokemonTrainer.getName());
        assertEquals(expectedPokemonTrainer.getTeam(), actualPokemonTrainer.getTeam());
    }
}
