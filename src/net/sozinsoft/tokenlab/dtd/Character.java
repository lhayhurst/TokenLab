
package net.sozinsoft.tokenlab.dtd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bookinfo",
    "pathfindersociety",
    "race",
    "alignment",
    "templates",
    "size",
    "deity",
    "challengerating",
    "xpaward",
    "classes",
    "factions",
    "types",
    "subtypes",
    "heropoints",
    "senses",
    "auras",
    "favoredclasses",
    "health",
    "xp",
    "money",
    "personal",
    "languages",
    "attributes",
    "saves",
    "defensive",
    "damagereduction",
    "immunities",
    "resistances",
    "weaknesses",
    "armorclass",
    "penalties",
    "maneuvers",
    "initiative",
    "movement",
    "encumbrance",
    "skills",
    "skillabilities",
    "feats",
    "traits",
    "flaws",
    "skilltricks",
    "animaltricks",
    "attack",
    "melee",
    "ranged",
    "defenses",
    "magicitems",
    "gear",
    "spelllike",
    "trackedresources",
    "otherspecials",
    "spellsknown",
    "spellsmemorized",
    "spellbook",
    "spellclasses",
    "journals",
    "images",
    "validation",
    "settings",
    "npc",
    "minions"
})
@XmlRootElement(name = "character")
public class Character {

    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String active;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String playername;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String nature;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String role;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String relationship;
    @XmlAttribute
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String type;
    protected Bookinfo bookinfo;
    protected Pathfindersociety pathfindersociety;
    @XmlElement(required = true)
    protected Race race;
    @XmlElement(required = true)
    protected Alignment alignment;
    @XmlElement(required = true)
    protected Templates templates;
    @XmlElement(required = true)
    protected Size size;
    @XmlElement(required = true)
    protected Deity deity;
    @XmlElement(required = true)
    protected Challengerating challengerating;
    @XmlElement(required = true)
    protected Xpaward xpaward;
    @XmlElement(required = true)
    protected Classes classes;
    @XmlElement(required = true)
    protected Factions factions;
    @XmlElement(required = true)
    protected Types types;
    @XmlElement(required = true)
    protected Subtypes subtypes;
    @XmlElement(required = true)
    protected Heropoints heropoints;
    @XmlElement(required = true)
    protected Senses senses;
    @XmlElement(required = true)
    protected Auras auras;
    @XmlElement(required = true)
    protected Favoredclasses favoredclasses;
    @XmlElement(required = true)
    protected Health health;
    @XmlElement(required = true)
    protected Xp xp;
    @XmlElement(required = true)
    protected Money money;
    @XmlElement(required = true)
    protected Personal personal;
    @XmlElement(required = true)
    protected Languages languages;
    @XmlElement(required = true)
    protected Attributes attributes;
    @XmlElement(required = true)
    protected Saves saves;
    @XmlElement(required = true)
    protected Defensive defensive;
    @XmlElement(required = true)
    protected Damagereduction damagereduction;
    @XmlElement(required = true)
    protected Immunities immunities;
    @XmlElement(required = true)
    protected Resistances resistances;
    @XmlElement(required = true)
    protected Weaknesses weaknesses;
    @XmlElement(required = true)
    protected Armorclass armorclass;
    @XmlElement(required = true)
    protected Penalties penalties;
    @XmlElement(required = true)
    protected Maneuvers maneuvers;
    @XmlElement(required = true)
    protected Initiative initiative;
    @XmlElement(required = true)
    protected Movement movement;
    @XmlElement(required = true)
    protected Encumbrance encumbrance;
    @XmlElement(required = true)
    protected Skills skills;
    @XmlElement(required = true)
    protected Skillabilities skillabilities;
    @XmlElement(required = true)
    protected Feats feats;
    @XmlElement(required = true)
    protected Traits traits;
    @XmlElement(required = true)
    protected Flaws flaws;
    @XmlElement(required = true)
    protected Skilltricks skilltricks;
    @XmlElement(required = true)
    protected Animaltricks animaltricks;
    @XmlElement(required = true)
    protected Attack attack;
    @XmlElement(required = true)
    protected Melee melee;
    @XmlElement(required = true)
    protected Ranged ranged;
    @XmlElement(required = true)
    protected Defenses defenses;
    @XmlElement(required = true)
    protected Magicitems magicitems;
    @XmlElement(required = true)
    protected Gear gear;
    @XmlElement(required = true)
    protected Spelllike spelllike;
    @XmlElement(required = true)
    protected Trackedresources trackedresources;
    @XmlElement(required = true)
    protected Otherspecials otherspecials;
    @XmlElement(required = true)
    protected Spellsknown spellsknown;
    @XmlElement(required = true)
    protected Spellsmemorized spellsmemorized;
    @XmlElement(required = true)
    protected Spellbook spellbook;
    @XmlElement(required = true)
    protected Spellclasses spellclasses;
    @XmlElement(required = true)
    protected Journals journals;
    protected Images images;
    @XmlElement(required = true)
    protected Validation validation;
    @XmlElement(required = true)
    protected Settings settings;
    protected Npc npc;
    @XmlElement(required = true)
    protected Minions minions;

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActive() {
        if (active == null) {
            return "no";
        } else {
            return active;
        }
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActive(String value) {
        this.active = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    public String toString() {
        return this.getName();
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the playername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlayername() {
        return playername;
    }

    /**
     * Sets the value of the playername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlayername(String value) {
        this.playername = value;
    }

    /**
     * Gets the value of the nature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNature() {
        if (nature == null) {
            return "normal";
        } else {
            return nature;
        }
    }

    /**
     * Sets the value of the nature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNature(String value) {
        this.nature = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        if (role == null) {
            return "pc";
        } else {
            return role;
        }
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationship() {
        if (relationship == null) {
            return "ally";
        } else {
            return relationship;
        }
    }

    /**
     * Sets the value of the relationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationship(String value) {
        this.relationship = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "Hero";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the bookinfo property.
     * 
     * @return
     *     possible object is
     *     {@link Bookinfo }
     *     
     */
    public Bookinfo getBookinfo() {
        return bookinfo;
    }

    /**
     * Sets the value of the bookinfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bookinfo }
     *     
     */
    public void setBookinfo(Bookinfo value) {
        this.bookinfo = value;
    }

    /**
     * Gets the value of the pathfindersociety property.
     * 
     * @return
     *     possible object is
     *     {@link Pathfindersociety }
     *     
     */
    public Pathfindersociety getPathfindersociety() {
        return pathfindersociety;
    }

    /**
     * Sets the value of the pathfindersociety property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pathfindersociety }
     *     
     */
    public void setPathfindersociety(Pathfindersociety value) {
        this.pathfindersociety = value;
    }

    /**
     * Gets the value of the race property.
     * 
     * @return
     *     possible object is
     *     {@link Race }
     *     
     */
    public Race getRace() {
        return race;
    }

    /**
     * Sets the value of the race property.
     * 
     * @param value
     *     allowed object is
     *     {@link Race }
     *     
     */
    public void setRace(Race value) {
        this.race = value;
    }

    /**
     * Gets the value of the alignment property.
     * 
     * @return
     *     possible object is
     *     {@link Alignment }
     *     
     */
    public Alignment getAlignment() {
        return alignment;
    }

    /**
     * Sets the value of the alignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Alignment }
     *     
     */
    public void setAlignment(Alignment value) {
        this.alignment = value;
    }

    /**
     * Gets the value of the templates property.
     * 
     * @return
     *     possible object is
     *     {@link Templates }
     *     
     */
    public Templates getTemplates() {
        return templates;
    }

    /**
     * Sets the value of the templates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Templates }
     *     
     */
    public void setTemplates(Templates value) {
        this.templates = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link Size }
     *     
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link Size }
     *     
     */
    public void setSize(Size value) {
        this.size = value;
    }

    /**
     * Gets the value of the deity property.
     * 
     * @return
     *     possible object is
     *     {@link Deity }
     *     
     */
    public Deity getDeity() {
        return deity;
    }

    /**
     * Sets the value of the deity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Deity }
     *     
     */
    public void setDeity(Deity value) {
        this.deity = value;
    }

    /**
     * Gets the value of the challengerating property.
     * 
     * @return
     *     possible object is
     *     {@link Challengerating }
     *     
     */
    public Challengerating getChallengerating() {
        return challengerating;
    }

    /**
     * Sets the value of the challengerating property.
     * 
     * @param value
     *     allowed object is
     *     {@link Challengerating }
     *     
     */
    public void setChallengerating(Challengerating value) {
        this.challengerating = value;
    }

    /**
     * Gets the value of the xpaward property.
     * 
     * @return
     *     possible object is
     *     {@link Xpaward }
     *     
     */
    public Xpaward getXpaward() {
        return xpaward;
    }

    /**
     * Sets the value of the xpaward property.
     * 
     * @param value
     *     allowed object is
     *     {@link Xpaward }
     *     
     */
    public void setXpaward(Xpaward value) {
        this.xpaward = value;
    }

    /**
     * Gets the value of the classes property.
     * 
     * @return
     *     possible object is
     *     {@link Classes }
     *     
     */
    public Classes getClasses() {
        return classes;
    }

    /**
     * Sets the value of the classes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Classes }
     *     
     */
    public void setClasses(Classes value) {
        this.classes = value;
    }

    /**
     * Gets the value of the factions property.
     * 
     * @return
     *     possible object is
     *     {@link Factions }
     *     
     */
    public Factions getFactions() {
        return factions;
    }

    /**
     * Sets the value of the factions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Factions }
     *     
     */
    public void setFactions(Factions value) {
        this.factions = value;
    }

    /**
     * Gets the value of the types property.
     * 
     * @return
     *     possible object is
     *     {@link Types }
     *     
     */
    public Types getTypes() {
        return types;
    }

    /**
     * Sets the value of the types property.
     * 
     * @param value
     *     allowed object is
     *     {@link Types }
     *     
     */
    public void setTypes(Types value) {
        this.types = value;
    }

    /**
     * Gets the value of the subtypes property.
     * 
     * @return
     *     possible object is
     *     {@link Subtypes }
     *     
     */
    public Subtypes getSubtypes() {
        return subtypes;
    }

    /**
     * Sets the value of the subtypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subtypes }
     *     
     */
    public void setSubtypes(Subtypes value) {
        this.subtypes = value;
    }

    /**
     * Gets the value of the heropoints property.
     * 
     * @return
     *     possible object is
     *     {@link Heropoints }
     *     
     */
    public Heropoints getHeropoints() {
        return heropoints;
    }

    /**
     * Sets the value of the heropoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Heropoints }
     *     
     */
    public void setHeropoints(Heropoints value) {
        this.heropoints = value;
    }

    /**
     * Gets the value of the senses property.
     * 
     * @return
     *     possible object is
     *     {@link Senses }
     *     
     */
    public Senses getSenses() {
        return senses;
    }

    /**
     * Sets the value of the senses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Senses }
     *     
     */
    public void setSenses(Senses value) {
        this.senses = value;
    }

    /**
     * Gets the value of the auras property.
     * 
     * @return
     *     possible object is
     *     {@link Auras }
     *     
     */
    public Auras getAuras() {
        return auras;
    }

    /**
     * Sets the value of the auras property.
     * 
     * @param value
     *     allowed object is
     *     {@link Auras }
     *     
     */
    public void setAuras(Auras value) {
        this.auras = value;
    }

    /**
     * Gets the value of the favoredclasses property.
     * 
     * @return
     *     possible object is
     *     {@link Favoredclasses }
     *     
     */
    public Favoredclasses getFavoredclasses() {
        return favoredclasses;
    }

    /**
     * Sets the value of the favoredclasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Favoredclasses }
     *     
     */
    public void setFavoredclasses(Favoredclasses value) {
        this.favoredclasses = value;
    }

    /**
     * Gets the value of the health property.
     * 
     * @return
     *     possible object is
     *     {@link Health }
     *     
     */
    public Health getHealth() {
        return health;
    }

    /**
     * Sets the value of the health property.
     * 
     * @param value
     *     allowed object is
     *     {@link Health }
     *     
     */
    public void setHealth(Health value) {
        this.health = value;
    }

    /**
     * Gets the value of the xp property.
     * 
     * @return
     *     possible object is
     *     {@link Xp }
     *     
     */
    public Xp getXp() {
        return xp;
    }

    /**
     * Sets the value of the xp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Xp }
     *     
     */
    public void setXp(Xp value) {
        this.xp = value;
    }

    /**
     * Gets the value of the money property.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getMoney() {
        return money;
    }

    /**
     * Sets the value of the money property.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setMoney(Money value) {
        this.money = value;
    }

    /**
     * Gets the value of the personal property.
     * 
     * @return
     *     possible object is
     *     {@link Personal }
     *     
     */
    public Personal getPersonal() {
        return personal;
    }

    /**
     * Sets the value of the personal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Personal }
     *     
     */
    public void setPersonal(Personal value) {
        this.personal = value;
    }

    /**
     * Gets the value of the languages property.
     * 
     * @return
     *     possible object is
     *     {@link Languages }
     *     
     */
    public Languages getLanguages() {
        return languages;
    }

    /**
     * Sets the value of the languages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Languages }
     *     
     */
    public void setLanguages(Languages value) {
        this.languages = value;
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link Attributes }
     *     
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attributes }
     *     
     */
    public void setAttributes(Attributes value) {
        this.attributes = value;
    }

    /**
     * Gets the value of the saves property.
     * 
     * @return
     *     possible object is
     *     {@link Saves }
     *     
     */
    public Saves getSaves() {
        return saves;
    }

    /**
     * Sets the value of the saves property.
     * 
     * @param value
     *     allowed object is
     *     {@link Saves }
     *     
     */
    public void setSaves(Saves value) {
        this.saves = value;
    }

    /**
     * Gets the value of the defensive property.
     * 
     * @return
     *     possible object is
     *     {@link Defensive }
     *     
     */
    public Defensive getDefensive() {
        return defensive;
    }

    /**
     * Sets the value of the defensive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Defensive }
     *     
     */
    public void setDefensive(Defensive value) {
        this.defensive = value;
    }

    /**
     * Gets the value of the damagereduction property.
     * 
     * @return
     *     possible object is
     *     {@link Damagereduction }
     *     
     */
    public Damagereduction getDamagereduction() {
        return damagereduction;
    }

    /**
     * Sets the value of the damagereduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Damagereduction }
     *     
     */
    public void setDamagereduction(Damagereduction value) {
        this.damagereduction = value;
    }

    /**
     * Gets the value of the immunities property.
     * 
     * @return
     *     possible object is
     *     {@link Immunities }
     *     
     */
    public Immunities getImmunities() {
        return immunities;
    }

    /**
     * Sets the value of the immunities property.
     * 
     * @param value
     *     allowed object is
     *     {@link Immunities }
     *     
     */
    public void setImmunities(Immunities value) {
        this.immunities = value;
    }

    /**
     * Gets the value of the resistances property.
     * 
     * @return
     *     possible object is
     *     {@link Resistances }
     *     
     */
    public Resistances getResistances() {
        return resistances;
    }

    /**
     * Sets the value of the resistances property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resistances }
     *     
     */
    public void setResistances(Resistances value) {
        this.resistances = value;
    }

    /**
     * Gets the value of the weaknesses property.
     * 
     * @return
     *     possible object is
     *     {@link Weaknesses }
     *     
     */
    public Weaknesses getWeaknesses() {
        return weaknesses;
    }

    /**
     * Sets the value of the weaknesses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Weaknesses }
     *     
     */
    public void setWeaknesses(Weaknesses value) {
        this.weaknesses = value;
    }

    /**
     * Gets the value of the armorclass property.
     * 
     * @return
     *     possible object is
     *     {@link Armorclass }
     *     
     */
    public Armorclass getArmorclass() {
        return armorclass;
    }

    /**
     * Sets the value of the armorclass property.
     * 
     * @param value
     *     allowed object is
     *     {@link Armorclass }
     *     
     */
    public void setArmorclass(Armorclass value) {
        this.armorclass = value;
    }

    /**
     * Gets the value of the penalties property.
     * 
     * @return
     *     possible object is
     *     {@link Penalties }
     *     
     */
    public Penalties getPenalties() {
        return penalties;
    }

    /**
     * Sets the value of the penalties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Penalties }
     *     
     */
    public void setPenalties(Penalties value) {
        this.penalties = value;
    }

    /**
     * Gets the value of the maneuvers property.
     * 
     * @return
     *     possible object is
     *     {@link Maneuvers }
     *     
     */
    public Maneuvers getManeuvers() {
        return maneuvers;
    }

    /**
     * Sets the value of the maneuvers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Maneuvers }
     *     
     */
    public void setManeuvers(Maneuvers value) {
        this.maneuvers = value;
    }

    /**
     * Gets the value of the initiative property.
     * 
     * @return
     *     possible object is
     *     {@link Initiative }
     *     
     */
    public Initiative getInitiative() {
        return initiative;
    }

    /**
     * Sets the value of the initiative property.
     * 
     * @param value
     *     allowed object is
     *     {@link Initiative }
     *     
     */
    public void setInitiative(Initiative value) {
        this.initiative = value;
    }

    /**
     * Gets the value of the movement property.
     * 
     * @return
     *     possible object is
     *     {@link Movement }
     *     
     */
    public Movement getMovement() {
        return movement;
    }

    /**
     * Sets the value of the movement property.
     * 
     * @param value
     *     allowed object is
     *     {@link Movement }
     *     
     */
    public void setMovement(Movement value) {
        this.movement = value;
    }

    /**
     * Gets the value of the encumbrance property.
     * 
     * @return
     *     possible object is
     *     {@link Encumbrance }
     *     
     */
    public Encumbrance getEncumbrance() {
        return encumbrance;
    }

    /**
     * Sets the value of the encumbrance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Encumbrance }
     *     
     */
    public void setEncumbrance(Encumbrance value) {
        this.encumbrance = value;
    }

    /**
     * Gets the value of the skills property.
     * 
     * @return
     *     possible object is
     *     {@link Skills }
     *     
     */
    public Skills getSkills() {
        return skills;
    }

    /**
     * Sets the value of the skills property.
     * 
     * @param value
     *     allowed object is
     *     {@link Skills }
     *     
     */
    public void setSkills(Skills value) {
        this.skills = value;
    }

    /**
     * Gets the value of the skillabilities property.
     * 
     * @return
     *     possible object is
     *     {@link Skillabilities }
     *     
     */
    public Skillabilities getSkillabilities() {
        return skillabilities;
    }

    /**
     * Sets the value of the skillabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link Skillabilities }
     *     
     */
    public void setSkillabilities(Skillabilities value) {
        this.skillabilities = value;
    }

    /**
     * Gets the value of the feats property.
     * 
     * @return
     *     possible object is
     *     {@link Feats }
     *     
     */
    public Feats getFeats() {
        return feats;
    }

    /**
     * Sets the value of the feats property.
     * 
     * @param value
     *     allowed object is
     *     {@link Feats }
     *     
     */
    public void setFeats(Feats value) {
        this.feats = value;
    }

    /**
     * Gets the value of the traits property.
     * 
     * @return
     *     possible object is
     *     {@link Traits }
     *     
     */
    public Traits getTraits() {
        return traits;
    }

    /**
     * Sets the value of the traits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Traits }
     *     
     */
    public void setTraits(Traits value) {
        this.traits = value;
    }

    /**
     * Gets the value of the flaws property.
     * 
     * @return
     *     possible object is
     *     {@link Flaws }
     *     
     */
    public Flaws getFlaws() {
        return flaws;
    }

    /**
     * Sets the value of the flaws property.
     * 
     * @param value
     *     allowed object is
     *     {@link Flaws }
     *     
     */
    public void setFlaws(Flaws value) {
        this.flaws = value;
    }

    /**
     * Gets the value of the skilltricks property.
     * 
     * @return
     *     possible object is
     *     {@link Skilltricks }
     *     
     */
    public Skilltricks getSkilltricks() {
        return skilltricks;
    }

    /**
     * Sets the value of the skilltricks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Skilltricks }
     *     
     */
    public void setSkilltricks(Skilltricks value) {
        this.skilltricks = value;
    }

    /**
     * Gets the value of the animaltricks property.
     * 
     * @return
     *     possible object is
     *     {@link Animaltricks }
     *     
     */
    public Animaltricks getAnimaltricks() {
        return animaltricks;
    }

    /**
     * Sets the value of the animaltricks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Animaltricks }
     *     
     */
    public void setAnimaltricks(Animaltricks value) {
        this.animaltricks = value;
    }

    /**
     * Gets the value of the attack property.
     * 
     * @return
     *     possible object is
     *     {@link Attack }
     *     
     */
    public Attack getAttack() {
        return attack;
    }

    /**
     * Sets the value of the attack property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attack }
     *     
     */
    public void setAttack(Attack value) {
        this.attack = value;
    }

    /**
     * Gets the value of the melee property.
     * 
     * @return
     *     possible object is
     *     {@link Melee }
     *     
     */
    public Melee getMelee() {
        return melee;
    }

    /**
     * Sets the value of the melee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Melee }
     *     
     */
    public void setMelee(Melee value) {
        this.melee = value;
    }

    /**
     * Gets the value of the ranged property.
     * 
     * @return
     *     possible object is
     *     {@link Ranged }
     *     
     */
    public Ranged getRanged() {
        return ranged;
    }

    /**
     * Sets the value of the ranged property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ranged }
     *     
     */
    public void setRanged(Ranged value) {
        this.ranged = value;
    }

    /**
     * Gets the value of the defenses property.
     * 
     * @return
     *     possible object is
     *     {@link Defenses }
     *     
     */
    public Defenses getDefenses() {
        return defenses;
    }

    /**
     * Sets the value of the defenses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Defenses }
     *     
     */
    public void setDefenses(Defenses value) {
        this.defenses = value;
    }

    /**
     * Gets the value of the magicitems property.
     * 
     * @return
     *     possible object is
     *     {@link Magicitems }
     *     
     */
    public Magicitems getMagicitems() {
        return magicitems;
    }

    /**
     * Sets the value of the magicitems property.
     * 
     * @param value
     *     allowed object is
     *     {@link Magicitems }
     *     
     */
    public void setMagicitems(Magicitems value) {
        this.magicitems = value;
    }

    /**
     * Gets the value of the gear property.
     * 
     * @return
     *     possible object is
     *     {@link Gear }
     *     
     */
    public Gear getGear() {
        return gear;
    }

    /**
     * Sets the value of the gear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gear }
     *     
     */
    public void setGear(Gear value) {
        this.gear = value;
    }

    /**
     * Gets the value of the spelllike property.
     * 
     * @return
     *     possible object is
     *     {@link Spelllike }
     *     
     */
    public Spelllike getSpelllike() {
        return spelllike;
    }

    /**
     * Sets the value of the spelllike property.
     * 
     * @param value
     *     allowed object is
     *     {@link Spelllike }
     *     
     */
    public void setSpelllike(Spelllike value) {
        this.spelllike = value;
    }

    /**
     * Gets the value of the trackedresources property.
     * 
     * @return
     *     possible object is
     *     {@link Trackedresources }
     *     
     */
    public Trackedresources getTrackedresources() {
        return trackedresources;
    }

    /**
     * Sets the value of the trackedresources property.
     * 
     * @param value
     *     allowed object is
     *     {@link Trackedresources }
     *     
     */
    public void setTrackedresources(Trackedresources value) {
        this.trackedresources = value;
    }

    /**
     * Gets the value of the otherspecials property.
     * 
     * @return
     *     possible object is
     *     {@link Otherspecials }
     *     
     */
    public Otherspecials getOtherspecials() {
        return otherspecials;
    }

    /**
     * Sets the value of the otherspecials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Otherspecials }
     *     
     */
    public void setOtherspecials(Otherspecials value) {
        this.otherspecials = value;
    }

    /**
     * Gets the value of the spellsknown property.
     * 
     * @return
     *     possible object is
     *     {@link Spellsknown }
     *     
     */
    public Spellsknown getSpellsknown() {
        return spellsknown;
    }

    /**
     * Sets the value of the spellsknown property.
     * 
     * @param value
     *     allowed object is
     *     {@link Spellsknown }
     *     
     */
    public void setSpellsknown(Spellsknown value) {
        this.spellsknown = value;
    }

    /**
     * Gets the value of the spellsmemorized property.
     * 
     * @return
     *     possible object is
     *     {@link Spellsmemorized }
     *     
     */
    public Spellsmemorized getSpellsmemorized() {
        return spellsmemorized;
    }

    /**
     * Sets the value of the spellsmemorized property.
     * 
     * @param value
     *     allowed object is
     *     {@link Spellsmemorized }
     *     
     */
    public void setSpellsmemorized(Spellsmemorized value) {
        this.spellsmemorized = value;
    }

    /**
     * Gets the value of the spellbook property.
     * 
     * @return
     *     possible object is
     *     {@link Spellbook }
     *     
     */
    public Spellbook getSpellbook() {
        return spellbook;
    }

    /**
     * Sets the value of the spellbook property.
     * 
     * @param value
     *     allowed object is
     *     {@link Spellbook }
     *     
     */
    public void setSpellbook(Spellbook value) {
        this.spellbook = value;
    }

    /**
     * Gets the value of the spellclasses property.
     * 
     * @return
     *     possible object is
     *     {@link Spellclasses }
     *     
     */
    public Spellclasses getSpellclasses() {
        return spellclasses;
    }

    /**
     * Sets the value of the spellclasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Spellclasses }
     *     
     */
    public void setSpellclasses(Spellclasses value) {
        this.spellclasses = value;
    }

    /**
     * Gets the value of the journals property.
     * 
     * @return
     *     possible object is
     *     {@link Journals }
     *     
     */
    public Journals getJournals() {
        return journals;
    }

    /**
     * Sets the value of the journals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Journals }
     *     
     */
    public void setJournals(Journals value) {
        this.journals = value;
    }

    /**
     * Gets the value of the images property.
     * 
     * @return
     *     possible object is
     *     {@link Images }
     *     
     */
    public Images getImages() {
        return images;
    }

    /**
     * Sets the value of the images property.
     * 
     * @param value
     *     allowed object is
     *     {@link Images }
     *     
     */
    public void setImages(Images value) {
        this.images = value;
    }

    /**
     * Gets the value of the validation property.
     * 
     * @return
     *     possible object is
     *     {@link Validation }
     *     
     */
    public Validation getValidation() {
        return validation;
    }

    /**
     * Sets the value of the validation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Validation }
     *     
     */
    public void setValidation(Validation value) {
        this.validation = value;
    }

    /**
     * Gets the value of the settings property.
     * 
     * @return
     *     possible object is
     *     {@link Settings }
     *     
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Sets the value of the settings property.
     * 
     * @param value
     *     allowed object is
     *     {@link Settings }
     *     
     */
    public void setSettings(Settings value) {
        this.settings = value;
    }

    /**
     * Gets the value of the npc property.
     * 
     * @return
     *     possible object is
     *     {@link Npc }
     *     
     */
    public Npc getNpc() {
        return npc;
    }

    /**
     * Sets the value of the npc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Npc }
     *     
     */
    public void setNpc(Npc value) {
        this.npc = value;
    }

    /**
     * Gets the value of the minions property.
     * 
     * @return
     *     possible object is
     *     {@link Minions }
     *     
     */
    public Minions getMinions() {
        return minions;
    }

    /**
     * Sets the value of the minions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Minions }
     *     
     */
    public void setMinions(Minions value) {
        this.minions = value;
    }

}
