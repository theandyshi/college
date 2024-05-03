package planetwars.strategies;

        import planetwars.publicapi.*;

        import java.util.*;

public class MyStrategy implements IStrategy {
    private Hashtable planetOwners = new Hashtable<>();

    public MyStrategy() {
        planetOwners =

    }

    public Dictionary<IVisiblePlanet,Integer> splitPlanet(List<IVisiblePlanet> planets){
        //returns dictionary with keys (SELF, OPPONENT, NEUTRAL) and values (IVisiblePlanet)


    }

    public void takeTurn(List<IPlanet> planets, IPlanetOperations planetOperations, Queue<IEvent> eventsToExecute) {
        List<IVisiblePlanet> SelfPlanets = new ArrayList<>();
        List<IVisiblePlanet> NeutralPlanets = new ArrayList<>();
        List<IVisiblePlanet> OpponentPlanets = new ArrayList<>();
        for (IPlanet planet : planets) {
            if (planet instanceof IVisiblePlanet && ((IVisiblePlanet) planet).getOwner() == Owner.SELF) {
                SelfPlanets.add((IVisiblePlanet) planet);
            } else if (planet instanceof IVisiblePlanet && ((IVisiblePlanet) planet).getOwner() == Owner.OPPONENT) {
                OpponentPlanets.add((IVisiblePlanet) planet);
            } else if (planet instanceof IVisiblePlanet) {
                NeutralPlanets.add((IVisiblePlanet) planet);
            }
        }
    }

    public String getName() {
        return "MyStrategy";
    }


    public boolean compete() {
        return false;
    }
}
