package com.example.xstudy.repositories

import androidx.lifecycle.ViewModel
import com.example.xstudy.domain.model.DidYouKnow
import com.example.xstudy.domain.model.MotivationQuote
import com.example.xstudy.domain.model.Users
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// AppViewModel is responsible for managing app-wide state
class AppViewModel : ViewModel() {


    private val _didYouKnow = MutableStateFlow(mutableListOf<DidYouKnow>(
        DidYouKnow(1, body = "The shortest war in history lasted only 38 minutes, between Britain and Zanzibar in 1896."),
        DidYouKnow(2, body = "A group of flamingos is called a 'flamboyance'."),
        DidYouKnow(3, body = "The Great Wall of China is not visible from space with the naked eye."),
        DidYouKnow(4, body = "Honeybees can recognize human faces."),
        DidYouKnow(5, body = "The longest word in the English language without a vowel is 'rhythms'."),
        DidYouKnow(6, body = "The fingerprints of koalas are virtually indistinguishable from human fingerprints."),
        DidYouKnow(7, body = "A day on Venus is longer than its year. Venus rotates so slowly that it takes 243 Earth days to complete one rotation."),
        DidYouKnow(8, body = "The loudest sound ever recorded was the eruption of Krakatoa in 1883, heard 3,000 miles away."),
        DidYouKnow(9, body = "The total weight of all the ants on Earth is greater than the total weight of all the humans on Earth."),
        DidYouKnow(10, body = "A jiffy is an actual unit of time: 1/100th of a second."),
        DidYouKnow(11, body = "The first oranges weren't orange. They were green."),
        DidYouKnow(12, body = "The Eiffel Tower can grow up to 6 inches taller in summer due to the expansion of iron in heat."),
        DidYouKnow(13, body = "Polar bears' fur is not white; it's actually clear and reflects light."),
        DidYouKnow(14, body = "The longest hiccuping spree lasted 68 years."),
        DidYouKnow(15, body = "Octopuses have three hearts."),
        DidYouKnow(16, body = "The world's oldest known living tree is over 5,000 years old."),
        DidYouKnow(17, body = "Bananas are berries, but strawberries aren't."),
        DidYouKnow(18, body = "A group of crows is called a 'murder'."),
        DidYouKnow(19, body = "The longest word in the English language is 189,819 letters long and would take over 3 hours to pronounce."),
        DidYouKnow(20, body = "The hashtag symbol is technically called an octothorpe."),
        DidYouKnow(21, body = "The dot over the letter 'i' is called a tittle."),
        DidYouKnow(22, body = "There are more possible iterations of a game of chess than there are atoms in the known universe."),
        DidYouKnow(23, body = "The longest English word without a vowel is 'rhythms'."),
        DidYouKnow(24, body = "The Great Barrier Reef is the largest living structure on Earth."),
        DidYouKnow(25, body = "A bolt of lightning is six times hotter than the surface of the sun."),
        DidYouKnow(26, body = "There are more trees on Earth than stars in the Milky Way galaxy."),
        DidYouKnow(27, body = "The human brain is more active during sleep than during the day."),
        DidYouKnow(28, body = "Humans share 50% of their DNA with bananas."),
        DidYouKnow(29, body = "The Sahara Desert was once a lush, green landscape."),
        DidYouKnow(30, body = "The world's largest desert is Antarctica, not the Sahara."),
        DidYouKnow(31, body = "The longest word in the English language without repeating a letter is 'uncopyrightable'."),
        DidYouKnow(32, body = "The inventor of the frisbee was turned into a frisbee after he died."),
        DidYouKnow(33, body = "The shortest war in history lasted 38 minutes between Britain and Zanzibar in 1896."),
        DidYouKnow(34, body = "The mouth of a jellyfish is also its anus."),
        DidYouKnow(35, body = "A group of pugs is called a 'grumble'."),
        DidYouKnow(36, body = "The longest place name in the world is 85 letters long."),
        DidYouKnow(37, body = "The scientific name for the Western Lowland Gorilla is 'Gorilla Gorilla Gorilla'."),
        DidYouKnow(38, body = "The Twitter bird's official name is Larry."),
        DidYouKnow(39, body = "There's a planet made of diamonds twice the size of Earth."),
        DidYouKnow(40, body = "The Dutch village of Giethoorn has no roads; all transportation is done by water."),
        DidYouKnow(41, body = "The longest word in the English language is 189,819 letters long."),
        DidYouKnow(42, body = "A group of hedgehogs is called a 'prickle'."),
        DidYouKnow(43, body = "The only letter that doesn't appear on the periodic table is 'J'."),
        DidYouKnow(44, body = "A day on Pluto is 6.4 Earth days long."),
        DidYouKnow(45, body = "The fingerprints of koalas are virtually indistinguishable from humans'."),
        DidYouKnow(46, body = "The smell of freshly-cut grass is actually a plant distress call."),
        DidYouKnow(47, body = "The Mongolian navy consists of a single tugboat with a crew of seven."),
        DidYouKnow(48, body = "The word 'strengths' is the longest word in the English language with only one vowel."),
        DidYouKnow(49, body = "Cats can't taste sweetness."),
        DidYouKnow(50, body = "The Hawaiian alphabet has only 12 letters.")
    ))

    val didYouKnow = _didYouKnow.asStateFlow()


    // MutableStateFlow for authentication status
    // Private to prevent direct modification from outside the ViewModel
    private val _authenticated = MutableStateFlow(false)

    // Public read-only StateFlow for observing authentication status
    val authenticated = _authenticated.asStateFlow()

    // MutableStateFlow for login screen visibility
    // Private to prevent direct modification from outside the ViewModel
    private val _isLogin = MutableStateFlow(false)

    // Public read-only StateFlow for observing login screen visibility
    val isLogin = _isLogin.asStateFlow()

    // MutableStateFlow for loading state
    // Private to prevent direct modification from outside the ViewModel
    private val _isLoading = MutableStateFlow(true)
    // Public read-only StateFlow for observing loading state
    val isLoading = _isLoading.asStateFlow()

    private val _isGuest = MutableStateFlow(true)

    val isGuest = _isGuest.asStateFlow()

    private val _isReturningUser = MutableStateFlow(true)

    val isReturningUser = _isReturningUser.asStateFlow()

    private val _isLogout = MutableStateFlow(true)

    val isLogout = _isLogout.asStateFlow()

    private val _isUsername = MutableStateFlow("")

    val isUsername = _isUsername.asStateFlow()

    private val _users = MutableStateFlow(mutableListOf<Users>())
    private val _motivationQuotes = MutableStateFlow(mutableListOf<MotivationQuote>())

    val users = _users.asStateFlow()
    val motivationQuotes = _motivationQuotes.asStateFlow()

    // Function to update users list
    fun setUsers(value: Users) {
        _users.value.add(value)
    }

    // Function to update motivation quotes list
    fun setMotivationQuotes(value: MotivationQuote) {
        _motivationQuotes.value.add(value)
    }

    // Function to update authentication status
    fun setAuthenticated(value: Boolean) {
        _authenticated.value = value
    }

    // Function to update login screen visibility
    fun setIsLogin(value: Boolean) {
        _isLogin.value = value
    }

    // Function to update loading state
    fun setIsLoading(value: Boolean) {
        _isLoading.value = value
    }

    // Function to update guest screen visibility
    fun setIsGuest(value: Boolean) {
        _isGuest.value = value
    }

    // Function to update returning user screen visibility
    fun setIsReturningUser(value: Boolean) {
        _isReturningUser.value = value
    }

    // Function to update logout screen visibility
    fun setIsLogout(value: Boolean) {
        _isLogout.value = value
    }

    // Function to update username screen visibility
    fun setIsUsername(value: String) {
        _isUsername.value = value
    }
}