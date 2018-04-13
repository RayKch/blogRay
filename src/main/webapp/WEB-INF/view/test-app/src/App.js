import React, { Component } from 'react';
import './App.css';
import Movie from './Movie'

const moviesTitle = [
	"Matrix"
	, "Full Metal Jacket"
	, "OldBoy"
	, "Star Wars"
];

const movieImages = [
	"https://upload.wikimedia.org/wikipedia/en/thumb/c/c1/The_Matrix_Poster.jpg/220px-The_Matrix_Poster.jpg"
	, "https://images-na.ssl-images-amazon.com/images/I/71qDKzqJZrL._SL1101_.jpg"
	, "https://upload.wikimedia.org/wikipedia/en/thumb/6/67/Oldboykoreanposter.jpg/220px-Oldboykoreanposter.jpg"
	, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Star_Wars_Logo.svg/1280px-Star_Wars_Logo.svg.png"
]

class App extends Component {
	/**
	 * [Render]
	 * description: 컴포넌트가 존재하기 시작할때 작동
	 * step.1 componentWillMount(): render 되기전 호출 (ex. api, ajax등 호출)
	 * step.2. render(): 화면에 그리기
	 * step.3. componentDidMount(): render 후 호출 (ex. databind?)

	 * [Update]
	 * description:
	 * step.1. componentWillReceiveProps(): 컴포넌트가 새로운 props를 받음
	 * step.2. shouldComponentUpdate(): old props, news props 비교 후 다르면 true라고 판단
	 * step.3. componentWillUpdate(): 2단계에서 true라고 판단되면 props update (ex. spinner 호출)
	 * step.4. render(): 화면에 그리기
	 * step.5. componentDidUpdate(): render 후 호출 (ex. spinner 제거)
	 */

	state = {
	}

	componentDidMount() {
		// setTimeout(() => {
		// 	this.setState({
		// 		movies: [
		// 			{
		// 				title:'Maxrix'
		// 				, poster:'https://upload.wikimedia.org/wikipedia/en/thumb/c/c1/The_Matrix_Poster.jpg/220px-The_Matrix_Poster.jpg'
		// 			}
		// 			, {
		// 				title:'Full Metal Jacket'
		// 				, poster:'https://images-na.ssl-images-amazon.com/images/I/71qDKzqJZrL._SL1101_.jpg'
		// 			}
		// 			, {
		// 				title:'OldBoy'
		// 				, poster:'https://upload.wikimedia.org/wikipedia/en/thumb/6/67/Oldboykoreanposter.jpg/220px-Oldboykoreanposter.jpg'
		// 			}
		// 			, {
		// 				title:'Star Wars'
		// 				, poster:'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Star_Wars_Logo.svg/1280px-Star_Wars_Logo.svg.png'
		// 			}
		// 			, {
		// 				title: "Trainsporting"
		// 				, poster: "https://i.ytimg.com/vi/VJkgwDHmXlw/maxresdefault.jpg"
		// 			}
		// 		]
		// 		, ...this.state.movies // 이전것을 두고 새로운것을 추가하라는 문법
		// 	})
		// }, 3000);
		fetch('https://yts.ag/api/v2/list_movies.json?sort_by=rating')
			.then(response => console.log(response))
			.catch(err => console.log(err))
	}

	_renderMovies = () => {
		const movies = this.state.movies.map((movie, index) => {
			return <Movie title={movie.title} poster={movie.poster} key={index}/>
		})
		return movies
	}

	render() {
		return (
			<div className="App">
				{this.state.movies ? this._renderMovies() : 'Loading..'}
			</div>
		);
	}
}

export default App;
